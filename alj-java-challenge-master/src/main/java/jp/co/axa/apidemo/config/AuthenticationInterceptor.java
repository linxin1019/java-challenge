package jp.co.axa.apidemo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: LIN
 * @createDate: 2023/4/23
 * @description: interceptor for authentication.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        // check the token in the headers
        String token = request.getHeader("token"); // retrieve token from the request headers
        if (token == null) {
            throw new RuntimeException("no token, please login");
        } else  if (!"123456".equals(token)) { // should use the token value calculated when user login
            throw new RuntimeException("invalid token，please login again");
        } else {
            // valid token, continue the business
            return true;
        }
    }
}

