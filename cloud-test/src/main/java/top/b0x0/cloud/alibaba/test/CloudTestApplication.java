package top.b0x0.cloud.alibaba.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ManJiis
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class CloudTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudTestApplication.class, args);
    }

    @RestController
//    @RequestMapping("/config")
    public class ConfigController {

        @Autowired
        ConfigurableApplicationContext applicationContext;

        @Value("${useLocalCache:false}")
        private boolean useLocalCache;

        @RequestMapping("/get")
        public boolean get() {
            return useLocalCache;
        }

        @GetMapping("/refreshEnv")
        public Object getEnv() {
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");

            //获取当前部署的环境
            String currentEnv = applicationContext.getEnvironment().getProperty("spring.profiles.active");
            System.err.println("in " + currentEnv + " enviroment; " + "user name :" + userName + "; age: " + userAge);

            HashMap<String, String> hashMap = new HashMap<>(8);
            hashMap.put(userName, userName);
            hashMap.put(userAge, userAge);
            return hashMap;
        }
    }
}
