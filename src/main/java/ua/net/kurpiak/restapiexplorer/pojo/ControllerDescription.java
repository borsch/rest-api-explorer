package ua.net.kurpiak.restapiexplorer.pojo;

import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.utils.Assertions;

import java.util.List;
import java.util.Objects;

import static ua.net.kurpiak.restapiexplorer.utils.Assertions.isEmpty;

public class ControllerDescription {

    private final Class<?> controller;

    private final ApiDescription apiDescription;

    private final List<MethodDescription<ApiEndpoint>> endpoints;

    private final String name;

    public ControllerDescription(Class<?> controller, ApiDescription apiDescription,
                                 List<MethodDescription<ApiEndpoint>> endpoints) {
        this.controller = controller;
        this.apiDescription = apiDescription;
        this.endpoints = endpoints;

        this.name = isEmpty(apiDescription.name()) ? controller.getSimpleName() : apiDescription.name();
    }

    public Class<?> getController() {
        return controller;
    }

    public ApiDescription getApiDescription() {
        return apiDescription;
    }

    public List<MethodDescription<ApiEndpoint>> getEndpoints() {
        return endpoints;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ControllerDescription that = (ControllerDescription) o;
        return Objects.equals(controller, that.controller)
               && Objects.equals(apiDescription, that.apiDescription)
               && Objects.equals(endpoints, that.endpoints)
               && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controller, apiDescription, endpoints, name);
    }

    @Override
    public String toString() {
        return "ControllerDescription:"
               + "\n\tcontroller=" + controller
               + "\n\tapiDescription=" + apiDescription
               + "\n\tname=" + name
               + "\n\tendpoints=" + endpoints
               + "\n";
    }
}
