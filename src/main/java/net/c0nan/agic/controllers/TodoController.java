package net.c0nan.agic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.c0nan.agic.models.ToDoItem;
import net.c0nan.agic.models.error.notfound.ToDoItemNotFoundError;
import net.c0nan.agic.models.error.validation.ToDoItemValidationError;
import net.c0nan.agic.models.request.ToDoItemAddRequest;
import net.c0nan.agic.models.request.ToDoItemUpdateRequest;
import net.c0nan.agic.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
@Api(value = "todo", tags = {"todo"}, description = "To Do List endpoints")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Validation Error", response = ToDoItemValidationError.class)
    })
    @ApiOperation(
            value = "Create a to do item",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = ToDoItem.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToDoItem postNew(@ApiParam(required = true) @RequestBody @Valid final ToDoItemAddRequest body) {
        return todoService.postNew(body);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found Error", response = ToDoItemNotFoundError.class)
    })
    @ApiOperation(
            value = "Retrieve a specific item by id",
            response = ToDoItem.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ToDoItem getItem(@ApiParam(required = true, example = "42") @PathVariable final Long id) {
        return todoService.get(id);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Validation Error", response = ToDoItemValidationError.class),
            @ApiResponse(code = 404, message = "Not Found Error", response = ToDoItemNotFoundError.class)
    })
    @ApiOperation(
            value = "Modify an item",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = ToDoItem.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToDoItem patchItem(@ApiParam(required = true, example = "42") @PathVariable final Long id, @ApiParam(required = true) @RequestBody final ToDoItemUpdateRequest body) {
        return todoService.update(id, body);
    }
}
