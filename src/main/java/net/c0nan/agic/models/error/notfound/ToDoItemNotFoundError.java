package net.c0nan.agic.models.error.notfound;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.c0nan.agic.models.error.TodoItemError;

@Data
public class ToDoItemNotFoundError extends TodoItemError<NotFoundErrorDetail> {
    @ApiModelProperty(example = "NotFoundError")
    @Override
    public String getName() {
        return super.getName();
    }
}
