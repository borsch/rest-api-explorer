package ua.net.kurpiak.restapiexplorer.ui.controllers;

import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.pojo.enums.RequestMethodType;

@ApiDescription(path = "/api/users")
public class UserController {

    @ApiEndpoint(path = "/", method = RequestMethodType.GET)
    public void getAllUsers() {

    }

    @ApiEndpoint(path = "/{id}", method = RequestMethodType.GET)
    public void getUserById() {

    }
}
