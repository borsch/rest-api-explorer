package ua.net.kurpiak.restapiexplorer.utils;

public class Assertions {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}