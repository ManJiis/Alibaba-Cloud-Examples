package top.b0x0.cloud.alibaba.consumer;

import com.alibaba.cloud.dubbo.env.DubboCloudProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(CloudConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerApplication.class, args);
        DubboCloudProperties dubboCloudProperties = new DubboCloudProperties();
        Set<String> subscribedServices = dubboCloudProperties.subscribedServices();
        log.info("CloudConsumerApplication ---> subscribedServices = {}", subscribedServices);
    }

}
