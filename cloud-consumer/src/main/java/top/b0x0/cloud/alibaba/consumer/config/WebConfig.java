package top.b0x0.cloud.alibaba.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * web config
 *
 * @author TANG
 * @since 2021-07-19
 * @since JDK1.8
 */
@Configuration
public class WebConfig {


    /**
     * 监听器：监听HTTP请求事件
     * 解决RequestContextHolder.getRequestAttributes()空指针问题
     *
     * @return /
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
