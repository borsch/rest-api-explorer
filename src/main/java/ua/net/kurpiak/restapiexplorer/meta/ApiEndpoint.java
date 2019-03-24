package ua.net.kurpiak.restapiexplorer.meta;

import ua.net.kurpiak.restapiexplorer.pojo.enums.RequestMethodType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiEndpoint {

    String path();

    String description() default "";

    RequestMethodType method();

    ApiHeaders headers() default @ApiHeaders({});

}
