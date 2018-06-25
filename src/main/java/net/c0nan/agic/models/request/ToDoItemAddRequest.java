package net.c0nan.agic.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel
public class ToDoItemAddRequest {
    @NotNull
    @Size(min = 1, max = 50)
    @ApiModelProperty(example = "Uulwi ifis halahs gag erh'ongg w'ssh.")
    private String text;
}
