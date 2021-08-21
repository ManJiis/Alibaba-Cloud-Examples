package top.b0x0.cloud.alibaba.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 模拟多实例 VMoptions：-Dserver.port=8089 -Xms500m -Xmx500m
 *
 * @author ManJiis
 */
@EnableDiscoveryClient
@EnableFeignClients
// @EnableDubbo
@SpringBootApplication
@EnableScheduling
public class CloudUserBootstrap {
    private static final Logger log = LoggerFactory.getLogger(CloudUserBootstrap.class);

    static {
        // 配置中心的缓存是一个容灾目录，在服务端不可用时，会读取客户端本地配置，如果禁用缓存，会失去容灾功能，这个点需要评估好；
        // 可以通过设置静态变量关闭缓存SnapShotSwitch.setIsSnapShot(false);
//        SnapShotSwitch.setIsSnapShot(false);

        // 服务注册缓存
        // https://lexburner.github.io/dubbo-nacos-stability/
        // -DnamingLoadCacheAtStart=false
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudUserBootstrap.class, args);
    }

    @RestController
    @RequestMapping("/config")
    public class ConfigController {

        @Autowired
        ConfigurableApplicationContext applicationContext;

        @GetMapping("/refreshEnv")
        public Object getEnv() {
            ConfigurableEnvironment env = applicationContext.getEnvironment();
            String appName = env.getProperty("spring.application.name");
            String serverPort = env.getProperty("server.port");
            String userName = env.getProperty("user.name");
            String userAge = env.getProperty("user.age");

            //获取当前部署的环境
            String currentEnv = env.getProperty("spring.profiles.active");
            System.err.println("appName " + appName + "serverPort :" + serverPort + ":");
            System.err.println("in " + currentEnv + " enviroment; " + "user name :" + userName + "; age: " + userAge);

            HashMap<String, String> hashMap = new HashMap<>(8);
            hashMap.put(userName, userName);
            hashMap.put(userAge, userAge);
            return hashMap;
        }
    }
}
