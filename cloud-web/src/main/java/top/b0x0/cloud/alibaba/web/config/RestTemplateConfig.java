package top.b0x0.cloud.alibaba.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 *
 * @author ManJiis
 * @since 2021-07-18
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 加上@LoadBalanced，请求地址要改成服务名
     *
     * @return /
     */
    @Bean
//    @LoadBalanced
//    @DubboTransported
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}