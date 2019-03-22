package ua.net.kurpiak.restapiexplorer.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.net.kurpiak.restapiexplorer.utils.Assertions;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ReflectionUtils {

    private static final Logger LOGGER = LogManager.getLogger(ReflectionUtils.class);

    private static final String PACKAGE_SEPARATOR = ".";

    private static final String FILE_SEPARATOR = "/";

    public static List<Class<?>> getAllClassesInPackage(final String packageName) {
        Assertions.notNull(packageName, "Package name can't be null");
        List<Class<?>> classes = new ArrayList<>();

        final String path = packageName.replace(PACKAGE_SEPARATOR, FILE_SEPARATOR);
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            Enumeration<URL> urls = classLoader.getResources(path);

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                File directory = new File(url.getPath());

                classes.addAll(getAllClassesInDirectory(directory, packageName));
            }
        } catch (Exception e) {
            LOGGER.error("Exception during reading classes inside package [{}]", packageName, e);

            throw new RuntimeException(e);
        }

        return classes;
    }

    private static List<Class<?>> getAllClassesInDirectory(File directory, String packageName)
        throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            return classes;
        }

        for (File file : files) {
            LOGGER.debug("found [{}] with path [{}]", file.isFile() ? "file" : "folder", file.getAbsolutePath());

            if (file.isDirectory()) {
                classes.addAll(getAllClassesInPackage(packageName + PACKAGE_SEPARATOR + file.getName()));
            } else if (isClassFile(file)) {
                Class<?> clazz = Class.forName(className(packageName, file));
                classes.add(clazz);
            }
        }

        return classes;
    }

    private static boolean isClassFile(File file) {
        return file.isFile() && file.getName().endsWith(".class");
    }

    private static String className(String packageName, File clazzFile) {
        return packageName + PACKAGE_SEPARATOR + clazzFile.getName().replace(".class", "");
    }

}
