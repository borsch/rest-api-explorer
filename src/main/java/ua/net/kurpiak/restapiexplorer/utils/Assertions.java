package ua.net.kurpiak.restapiexplorer.utils;

import java.util.Collection;

public class Assertions {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String s, String message) {
        if (isEmpty(s)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

}
