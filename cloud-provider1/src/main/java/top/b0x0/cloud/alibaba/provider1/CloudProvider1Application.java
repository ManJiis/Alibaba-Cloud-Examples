package top.b0x0.cloud.alibaba.provider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableScheduling
public class CloudProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider1Application.class, args);
    }

}
