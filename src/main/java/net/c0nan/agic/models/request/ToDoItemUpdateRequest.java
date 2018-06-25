package net.c0nan.agic.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ToDoItemUpdateRequest extends ToDoItemAddRequest {
    @ApiModelProperty(example = "true")
    private Boolean completed;
}
