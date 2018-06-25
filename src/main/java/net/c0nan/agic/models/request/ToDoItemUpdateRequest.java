package net.c0nan.agic.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ToDoItemUpdateRequest extends ToDoItemAddRequest {
    @ApiModelProperty(example = "true", position = 1, name = "isCompleted")
    @JsonProperty(value = "isCompleted")
    private Boolean completed;
}
