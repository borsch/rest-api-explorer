package ua.net.kurpiak.restapiexplorer.pojo;

import java.lang.reflect.Method;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MethodDescription<?> that = (MethodDescription<?>) o;
        return Objects.equals(method, that.method) && Objects.equals(annotation, that.annotation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, annotation);
    }

    @Override
    public String toString() {
        return "\n\t\tMethodDescription:"
               + "\n\t\t\tmethod=" + method
               + "\n\t\t\tannotation=" + annotation
               + "\n";
    }
}
