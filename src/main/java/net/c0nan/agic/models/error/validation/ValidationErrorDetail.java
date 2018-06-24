package net.c0nan.agic.models.error.validation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.c0nan.agic.models.error.ErrorDetail;

@Data
public class ValidationErrorDetail extends ErrorDetail {

    public ValidationErrorDetail() {
    }

    public ValidationErrorDetail(final String location, final String param, final String msg, final String value) {
        this.location = location;
        this.param = param;
        this.msg = msg;
        this.value = value;
    }

    @ApiModelProperty(example = "params")
    private String location;
    @ApiModelProperty(example = "text", position = 1)
    private String param;
    @ApiModelProperty(example = "Must be between 1 and 50 chars long", position = 2)
    private String msg;
    @ApiModelProperty(example = "", position = 3)
    private String value;
}
