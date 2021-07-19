package top.b0x0.cloud.alibaba.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ManJiis
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling
public class CloudAuthBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthBootstrap.class, args);
    }

}
