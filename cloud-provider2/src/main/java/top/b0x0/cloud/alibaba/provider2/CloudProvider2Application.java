package top.b0x0.cloud.alibaba.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableScheduling
public class CloudProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider2Application.class, args);
    }

}
