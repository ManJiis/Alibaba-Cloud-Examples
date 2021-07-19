package top.b0x0.cloud.alibaba.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CloudUserBootstrap {
    private static final Logger log = LoggerFactory.getLogger(CloudUserBootstrap.class);

    public static void main(String[] args) {
        SpringApplication.run(CloudUserBootstrap.class, args);
    }

}
