package top.b0x0.cloud.alibaba.consumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.api.EchoService;

/**
 * 回声测试
 *
 * @author TANG
 * @date 2021-07-16
 * @since jdk1.8
 */
@RestController
public class EchoController {

    @DubboReference(version = "${service.version}")
    private EchoService echoService;

    @Value("${service.version}")
    private String serviceVersion;

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        System.out.println("string = " + string);
        System.out.println("serviceVersion = " + serviceVersion);
        System.out.println("echoService = " + echoService);
        return echoService.echo(string);
    }

//    @Autowired
//    private LoadBalancerClient loadBalancerClient;
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${spring.application.name}")
//    private String appName;
//
//    @GetMapping("/echo/app-name")
//    public String echoAppName() {
//        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("cloud-provider");
//        String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
//        System.out.println("request url:" + url);
//        return restTemplate.getForObject(url, String.class);
//    }
}
