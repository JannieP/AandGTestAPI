package net.c0nan.agic.models.error.notfound;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.c0nan.agic.models.error.ErrorDetail;

@Data
public class NotFoundErrorDetail extends ErrorDetail {

    public NotFoundErrorDetail() {
    }

    public NotFoundErrorDetail(final String message) {
        this.message = message;
    }

    @ApiModelProperty(example = "Item with 9 not found")
    private String message;
}
