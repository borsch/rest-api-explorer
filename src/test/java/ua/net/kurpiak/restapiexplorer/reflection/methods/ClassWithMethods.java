package ua.net.kurpiak.restapiexplorer.reflection.methods;

import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.meta.ApiHeaders;
import ua.net.kurpiak.restapiexplorer.pojo.enums.RequestMethodType;

@ApiDescription(path = "/api/users", description = "Users API")
public class ClassWithMethods {

    @ApiEndpoint(
        path = "/{id}",
        method = RequestMethodType.GET,
        headers = @ApiHeaders({
            @ApiHeaders.ApiHeader(name = "Content-Type", value = "application/json")
        })
    )
    public String methodGet() {
        return "";
    }

    @ApiEndpoint(
        path = "/",
        method = RequestMethodType.POST,
        headers = @ApiHeaders({
            @ApiHeaders.ApiHeader(name = "Accept", value = "application/json"),
            @ApiHeaders.ApiHeader(name = "Content-Type", value = "application/json")
        })
    )
    public String methodPost() {
        return "";
    }

}
