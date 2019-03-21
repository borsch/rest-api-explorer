package ua.net.kurpiak.restapiexplorer;

import ua.net.kurpiak.restapiexplorer.utils.ReflectionUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Class<?>> classes = ReflectionUtils.getAllClassesInPackage("ua.net.kurpiak.restapiexplorer");

        System.out.println(classes);
    }

}
