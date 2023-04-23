package jp.co.axa.apidemo.common;

import lombok.Getter;
import lombok.ToString;

/**
 * @author: LIN
 * @createDate: 2023/4/22
 * @description: result code enum.
 */
@Getter
@ToString
public enum ResultCodeEnum {
    SUCCESS(0, "success"),
    EMPTY_RESULT(1, "empty result"),
    UPDATE_FAILURE(2, "update failure"),
    DELETE_FAILURE(3, "delete failure"),
    UPDATE_TARGET_NOT_EXIST(4, "update target not exist"),
    INTERNAL_ERROR(500, "internal error");

    private Integer code;
    private String message;
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
