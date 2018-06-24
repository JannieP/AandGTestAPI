package net.c0nan.agic.models.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BalanceTestResult {
    @ApiModelProperty(example = "\"[(]\"")
    private String input;
    @ApiModelProperty(example = "false", position = 1)
    private boolean balanced;
}
