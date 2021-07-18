package top.b0x0.cloud.alibaba.provider1.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ManJiis
 */
@EnableDiscoveryClient  //使用 @EnableDiscoveryClient 注解开启服务注册与发现功能
@Configuration
public class NacosDiscoveryConfiguration {

    /**
     * spring-cloud-starter-alibaba-sentinel
     *
     * @return /
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

/*    @Bean
    public void sentinelConfig() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource(
                "com.alibaba.cloud.examples.FooService:hello(java.lang.String)");
        flowRule.setCount(10);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }*/
}
