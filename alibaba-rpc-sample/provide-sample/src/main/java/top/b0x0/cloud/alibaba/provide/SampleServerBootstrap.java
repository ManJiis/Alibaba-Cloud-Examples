package top.b0x0.cloud.alibaba.provide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Dubbo Spring Cloud Server Bootstrap.
 *
 * @author ManJiis
 * @since 2021-08-05
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SampleServerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SampleServerBootstrap.class);
    }

}


