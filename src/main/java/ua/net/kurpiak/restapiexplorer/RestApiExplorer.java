package ua.net.kurpiak.restapiexplorer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.pojo.ControllerDescription;
import ua.net.kurpiak.restapiexplorer.pojo.MethodDescription;
import ua.net.kurpiak.restapiexplorer.reflection.ReflectionUtils;
import ua.net.kurpiak.restapiexplorer.ui.UiBuilder;
import ua.net.kurpiak.restapiexplorer.utils.Assertions;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static ua.net.kurpiak.restapiexplorer.reflection.ReflectionUtils.getAllMethodsWithAnnotation;
import static ua.net.kurpiak.restapiexplorer.reflection.ReflectionUtils.getAnnotation;
import static ua.net.kurpiak.restapiexplorer.utils.Assertions.notEmpty;

public class RestApiExplorer {

    private static final Logger LOGGER = LogManager.getLogger(RestApiExplorer.class);

    public static void init(String packageToScan) {
        List<Class<?>> classes = getAllControllers(packageToScan);
        List<ControllerDescription> controllers = classes.stream()
            .map(RestApiExplorer::buildControllerDescription)
            .collect(toList());

        LOGGER.trace("Controllers' descriptions \n{}", controllers);
        UiBuilder.build(controllers);
    }

    private static List<Class<?>> getAllControllers(String packageToScan) {
        notEmpty(packageToScan, "packageToScan can't be empty");
        LOGGER.debug("Init rest api for package: {}", packageToScan);

        List<Class<?>> classes = ReflectionUtils.getAllClassesInPackage(packageToScan);
        classes = ReflectionUtils.getAllClassesWithAnnotation(classes, ApiDescription.class);

        notEmpty(classes, String.format("Package [%s] doesn't contains classes with [%s] annotation", packageToScan, ApiDescription.class.getCanonicalName()));
        LOGGER.debug("Found {} classes with ApiDescription annotation", classes.size());

        return classes;
    }

    private static ControllerDescription buildControllerDescription(Class<?> clazz) {
        ApiDescription apiDescription = getAnnotation(clazz, ApiDescription.class);
        List<MethodDescription<ApiEndpoint>> endpoints = getAllMethodsWithAnnotation(clazz, ApiEndpoint.class);

        return new ControllerDescription(clazz, apiDescription, endpoints);
    }
}
