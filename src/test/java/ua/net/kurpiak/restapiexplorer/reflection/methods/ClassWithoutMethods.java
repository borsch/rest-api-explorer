package ua.net.kurpiak.restapiexplorer.reflection.methods;

import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.meta.ApiHeaders;
import ua.net.kurpiak.restapiexplorer.pojo.enums.RequestMethodType;

public class ClassWithoutMethods {

    @ApiEndpoint(
        path = "/{id}",
        method = RequestMethodType.GET,
        headers = @ApiHeaders({
            @ApiHeaders.ApiHeader(name = "Content-Type", value = "application/json")
        })
    )
    private void privateMethod() {

    }

}
