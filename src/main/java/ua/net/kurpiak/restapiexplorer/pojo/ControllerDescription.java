package ua.net.kurpiak.restapiexplorer.pojo;

import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;

import java.util.List;
import java.util.Objects;

public class ControllerDescription {

    private final Class<?> controller;

    private final ApiDescription apiDescription;

    private final List<MethodDescription<ApiEndpoint>> endpoints;

    public ControllerDescription(Class<?> controller, ApiDescription apiDescription,
                                 List<MethodDescription<ApiEndpoint>> endpoints) {
        this.controller = controller;
        this.apiDescription = apiDescription;
        this.endpoints = endpoints;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ControllerDescription that = (ControllerDescription) o;
        return Objects.equals(controller, that.controller) && Objects.equals(apiDescription, that.apiDescription)
               && Objects.equals(endpoints, that.endpoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(controller, apiDescription, endpoints);
    }

    @Override
    public String toString() {
        return "ControllerDescription:"
               + "\n\tcontroller=" + controller
               + "\n\tapiDescription=" + apiDescription
               + "\n\tendpoints=" + endpoints
               + "\n";
    }
}
