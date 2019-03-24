package ua.net.kurpiak.restapiexplorer.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.net.kurpiak.restapiexplorer.pojo.MethodDescription;
import ua.net.kurpiak.restapiexplorer.utils.Assertions;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class ReflectionUtils {

    private static final Logger LOGGER = LogManager.getLogger(ReflectionUtils.class);

    private static final String PACKAGE_SEPARATOR = ".";

    private static final String FILE_SEPARATOR = "/";

    /**
     * Get all classes in passed package
     * @param packageName - package name to be scanned
     * @return list of {@code Class}'es inside package and all nested packages
     */
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

    public static List<Class<?>> getAllClassesWithAnnotation(final List<Class<?>> classes, final Class<? extends Annotation> annotation) {
        return classes.stream()
            .filter(clazz -> clazz.isAnnotationPresent(annotation))
            .collect(toList());
    }

    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> annotation) {
        Assertions.notNull(clazz, "Class can't be null");
        Assertions.notNull(annotation, "Annotation can't be null");

        return clazz.getAnnotation(annotation);
    }

    public static <T extends Annotation> List<MethodDescription<T>> getAllMethodsWithAnnotation(Class<?> clazz, Class<T> annotation) {
        Method[] methods = clazz.getMethods();
        if (methods == null || methods.length == 0) {
            LOGGER.warn("Class [{}] has no public methods", clazz.getName());

            return emptyList();
        }

        return Arrays.stream(methods)
                     .filter(method -> method.isAnnotationPresent(annotation))
                     .map(method -> new MethodDescription<>(method, method.getAnnotation(annotation)))
                     .collect(toList());
    }

    private static boolean isClassFile(File file) {
        return file.isFile() && file.getName().endsWith(".class");
    }

    private static String className(String packageName, File clazzFile) {
        return packageName + PACKAGE_SEPARATOR + clazzFile.getName().replace(".class", "");
    }

}
