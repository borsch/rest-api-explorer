package ua.net.kurpiak.restapiexplorer.pojo;

import java.lang.reflect.Method;

public class MethodDescription<T> {

    private final Method method;
    private final T annotation;

    public MethodDescription(Method method, T annotation) {
        this.method = method;
        this.annotation = annotation;
    }

    public Method getMethod() {
        return method;
    }

    public T getAnnotation() {
        return annotation;
    }

    @Override
    public String toString() {
        return "MethodDescription{" + "method=" + method + ", annotation=" + annotation + '}';
    }
}
