package jp.co.axa.apidemo.common;

import lombok.Data;

/**
 * @author: LIN
 * @createDate: 2023/4/22
 * @description: this class is to format the response of the api.
 */
@Data
public class RequestResult {
    private Integer resultCode;
    private String message;
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
