package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.common.RequestResult;
import jp.co.axa.apidemo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: LIN
 * @createDate: 2023/4/23
 * @description: global exception handler for runtime errors.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public RequestResult handlerException(HttpServletRequest request, Exception e) {
        log.error("Exception occurred when requesting {}. Exception: {}", request.getRequestURL(), e);
        return new RequestResult(ResultCodeEnum.INTERNAL_ERROR, e.getMessage());
    }
}
