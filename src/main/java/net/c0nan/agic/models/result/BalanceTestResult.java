package net.c0nan.agic.models.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BalanceTestResult {
    @ApiModelProperty(example = "\"[(]\"")
    private String input;
    @ApiModelProperty(example = "false", position = 1, name = "isBalanced")
    @JsonProperty(value = "isBalanced")
    private boolean balanced;
}
