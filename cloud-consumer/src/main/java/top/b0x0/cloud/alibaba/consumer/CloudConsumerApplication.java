package top.b0x0.cloud.alibaba.consumer;

import com.alibaba.cloud.dubbo.env.DubboCloudProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Set;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableScheduling
public class CloudConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class, args);
        DubboCloudProperties dubboCloudProperties = new DubboCloudProperties();
        Set<String> strings = dubboCloudProperties.subscribedServices();
        System.out.println("strings = " + strings);
    }

    //实例化 RestTemplate 实例
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
