package jp.co.axa.apidemo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: LIN
 * @createDate: 2023/4/22
 * @description: this class is to format the response of the api.
 */
@ApiModel("response object")
@Data
public class RequestResult {
    @ApiModelProperty(value = "result code")
    private Integer resultCode;
    @ApiModelProperty(value = "detail message of the request result")
    private String message;
    @ApiModelProperty(value = "request result objects")
    private Object resultObjects;

    public RequestResult(ResultCodeEnum resultCode, Object objs) {
        this.resultCode = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.resultObjects = objs;
    }
    public RequestResult(Object objs) {
        this(ResultCodeEnum.SUCCESS, objs);
    }
}
