package net.c0nan.agic.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.c0nan.agic.models.error.validation.ToDoItemValidationError;
import net.c0nan.agic.models.result.BalanceTestResult;
import net.c0nan.agic.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@Api(value = "tasks", tags = {"tasks"}, description = "General algorithmic tasks")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Validation Error", response = ToDoItemValidationError.class)
    })
    @ApiOperation(
            value = "Checks if brackets in a string are balanced",
            notes = "Brackets in a string are considered to be balanced if the following criteria are met:\n"
                    + "\n"
                    + "* For every opening bracket (i.e., (, {, or [), there is a matching closing bracket (i.e., ), }, or ]) of the same type (i.e., ( matches ), "
                    + "{ matches }, and [ matches ]). An opening bracket must appear before (to the left of) its matching closing bracket. For example, ]{}[ is not balanced.\n"
                    + "* No unmatched braces lie between some pair of matched bracket. For example, ({[]}) is balanced, but {[}] and [{)] are not balanced.",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            response = BalanceTestResult.class)
    @RequestMapping(value = "/validateBrackets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BalanceTestResult validateBrackets(@ApiParam(value = "Input string (max length 100)", required = true) @RequestParam final String input) {
        taskService.validateInput(input);
        return taskService.validateBrackets(input);
    }

}
