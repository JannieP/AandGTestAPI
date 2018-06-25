package net.c0nan.agic.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ToDoItem {
    @ApiModelProperty(example = "42")
    private Integer id;
    @ApiModelProperty(example = "\"Uulwi ifis halahs gag erh'ongg w'ssh.\"", position = 1)
    private String text;
    @ApiModelProperty(example = "false", position = 2)
    private Boolean completed = false;
    @ApiModelProperty(example = "\"2017-10-13T01:50:58.735Z\"", position = 3)
    private String createdAt;
}
