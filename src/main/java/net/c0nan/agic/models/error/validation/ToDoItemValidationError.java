package net.c0nan.agic.models.error.validation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.c0nan.agic.models.error.TodoItemError;

@Data
public class ToDoItemValidationError extends TodoItemError<ValidationErrorDetail> {

    @ApiModelProperty(example = "ValidationError")
    @Override
    public String getName() {
        return super.getName();
    }
}
