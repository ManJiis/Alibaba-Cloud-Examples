package top.b0x0.cloud.alibaba.consumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.b0x0.cloud.alibaba.api.EchoService;
import top.b0x0.cloud.alibaba.common.util.ServiceList;
import top.b0x0.cloud.alibaba.common.vo.R;

import java.util.List;

/**
 * 回声测试
 *
 * @author ManJiis
 * @date 2021-07-16
 * @since jdk1.8
 */
@RestController
public class EchoController {
    private final Logger log = LoggerFactory.getLogger(EchoController.class);

    //    @DubboReference(version = "${service.version}", validation = "CustomValidator")
    @DubboReference(version = "${service.version}", group = "provide1")
    private EchoService echoService;

    @Value("${service.version}")
    private String serviceVersion;

    @GetMapping("/v1/echo/{string}")
    public R echo(@PathVariable String string) {
        log.info("string = {}", string);
        log.info("serviceVersion = {}", serviceVersion);
        log.info("echoService = {}", echoService);
        String hello = echoService.sayHello(string);
        return R.ok(hello);
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String consumerAppName;

    /**
     * 获取注册中心数据对象
     */
    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/v2/echo/app-name")
    public String echoAppName() {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceList.PROVIDER_1);
//        log.info("consumerAppName : {}", consumerAppName);
//        log.info("ServiceInstance : {}", serviceInstance);
//        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), consumerAppName);


        // 通过服务名获取到注册到nacos的ip+端口地址信息（集群会获取多个）
        List<ServiceInstance> instances = discoveryClient.getInstances(ServiceList.PROVIDER_1);
        ServiceInstance serviceInstance = instances.get(0);
        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), consumerAppName);

        log.info("consumerAppName : {}", consumerAppName);
        log.info("ServiceInstance : {}", serviceInstance);
        log.info("request url: {}", url);
        return restTemplate.getForObject(url, String.class);
    }
}
