package top.b0x0.cloud.alibaba.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ManJiis
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class CloudWebBootstrap {
    private static final Logger log = LoggerFactory.getLogger(CloudWebBootstrap.class);

    public static void main(String[] args) {
        SpringApplication.run(CloudWebBootstrap.class, args);
    }

}
