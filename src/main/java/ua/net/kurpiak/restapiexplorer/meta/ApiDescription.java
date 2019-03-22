package ua.net.kurpiak.restapiexplorer.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDescription {

    /**
     * Controller base path for all inside endpoints
     * example {@code @ApiDescription(path = "/api/users")}
     * @return base path for current controller
     */
    String path();

    /**
     * human readable description of what this controller do
     * @return human readable description
     */
    String description() default "";

}
