package ua.net.kurpiak.restapiexplorer.ui.controllers;

import ua.net.kurpiak.restapiexplorer.meta.ApiDescription;
import ua.net.kurpiak.restapiexplorer.meta.ApiEndpoint;
import ua.net.kurpiak.restapiexplorer.pojo.enums.RequestMethodType;

@ApiDescription(
    name = "Books",
    path = "/api/book",
    description = "All activity relative to books"
)
public class BookController {

    @ApiEndpoint(path = "/", method = RequestMethodType.GET)
    public void getAllBooks() {

    }

    @ApiEndpoint(
        path = "/{id}",
        method = RequestMethodType.DELETE,
        description = "Delete book by given ID"
    )
    public void deleteBook() {

    }

    @ApiEndpoint(path = "/{id}/comments", method = RequestMethodType.GET)
    public void countComments() {

    }
}
