package top.b0x0.cloud.alibaba.gateway.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ManJiis
 * @date 2021-11-10
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "b0x0.gateway.token-filter")
public class WhiteListConfig implements InitializingBean {

    private List<String> whiteList = new ArrayList<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("whiteList = " + whiteList);
    }
}