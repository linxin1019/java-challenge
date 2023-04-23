package jp.co.axa.apidemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LIN
 * @createDate: 2023/4/23
 * @description: interceptors config class.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list = new ArrayList<>();
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/api/v1**") // path pattern of which authentication should be checked
                .excludePathPatterns(list);
        super.addInterceptors(registry);
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
