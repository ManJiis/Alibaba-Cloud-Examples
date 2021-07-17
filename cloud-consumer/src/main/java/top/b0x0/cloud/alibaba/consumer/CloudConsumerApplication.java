package top.b0x0.cloud.alibaba.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableScheduling
public class CloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class, args);
    }

    //实例化 RestTemplate 实例
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
