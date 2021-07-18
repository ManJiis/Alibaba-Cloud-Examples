package top.b0x0.cloud.alibaba.provider1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableScheduling
public class CloudProvider1Application {
    private static final Logger log = LoggerFactory.getLogger(CloudProvider1Application.class);

    @Value("${spring.cloud.nacos.discovery.enabled}")
    private Boolean nacosDiscoveryEnabled;

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider1Application.class, args);
    }

    @RestController
    public class EchoController {

        @GetMapping(value = "/echo/{string}")
        public String echo(@PathVariable String string) {
            log.info("nacosDiscoveryEnabled: {}", nacosDiscoveryEnabled);
            return "Hello Nacos Discovery " + string;
        }
    }


}
