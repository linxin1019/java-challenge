package jp.co.axa.apidemo.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: LIN
 * @createDate: 2023/4/23
 * @description: filter config class.
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBean() {
        // enable the LogFilter
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new LogFilter());
        // specify the path patterns filters work on
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}