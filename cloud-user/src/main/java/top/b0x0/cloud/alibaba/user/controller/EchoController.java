package top.b0x0.cloud.alibaba.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.b0x0.cloud.alibaba.api.IEchoService;
import top.b0x0.cloud.alibaba.common.util.ServiceList;
import top.b0x0.cloud.alibaba.common.vo.R;

import java.net.URI;
import java.util.List;

/**
 * 回声测试
 *
 * @author ManJiis
 * @date 2021-07-16
 * @since jdk1.8
 */
@RestController
@RequestMapping("/user")
public class EchoController {
    private final Logger log = LoggerFactory.getLogger(EchoController.class);

    //    @DubboReference(version = "${service.version}", group = "b0x0-cloud-user")
    @Autowired
    private IEchoService userEchoService;

    //    @DubboReference(version = "${service.version}", group = "b0x0-cloud-auth")
    @Autowired
    private IEchoService authEchoService;


    @Value("${service.version}")
    private String serviceVersion;

    @GetMapping("/echo1/{string}/{string2}")
    public R echo1(@PathVariable("string") String string, @PathVariable("string2") String string2) {
        log.info("/user/echo1/{}/{}", string, string2);
//        log.info("echoService1 = {}", userEchoService);
//        log.info("echoService2 = {}", authEchoService);
        String sayHello = userEchoService.sayHello(string);
        log.info("sayHello: {}", sayHello);
        String bonjour = authEchoService.bonjour(string2);
        log.info("bonjour: {}", bonjour);
        return R.ok(sayHello + "\r\n" + bonjour);
    }

    @GetMapping("/echo2/{string}")
    public R echo2(@PathVariable String string) {
        log.info("string = {}", string);
        String hello = authEchoService.bonjour(string);
        return R.ok(hello);
    }

    @GetMapping("/echo3/{name}")
    public R echo3(@PathVariable String name) {
        return R.ok("say hello : " + name);
    }

    @GetMapping("/echo/{name}")
    public R echo(@PathVariable String name) {
        return R.ok("say hello : " + name);
    }

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String currentAppName;

    /**
     * 获取注册中心数据对象
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/v2/echo/app-name")
    public R echoAppName() {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceList.PROVIDER_1);
//        log.info("consumerAppName : {}", consumerAppName);
//        log.info("ServiceInstance : {}", serviceInstance);
//        String url = String.format("http://%s:%s/user/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), consumerAppName);


        List<String> services = discoveryClient.getServices();
        // [b0x0-cloud-user, b0x0-cloud-order, b0x0-cloud-auth]
        log.info("discoveryClient.getServices: {}", services);

        // 通过服务名获取到注册到nacos的ip+端口地址信息（集群会获取多个）
        List<ServiceInstance> instances = discoveryClient.getInstances(ServiceList.PROVIDER_USER);
        ServiceInstance serviceInstance = instances.get(0);
        URI uri = serviceInstance.getUri();
        // http://192.168.8.106:8091
        log.info("uri = {}", uri);
        String url = String.format("http://%s:%s/user/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), currentAppName);

//        String tmpUrl = "http://" + ServiceList.PROVIDER_1 + "/echo/%s";
//        String url = String.format(tmpUrl, currentAppName);


        // b0x0-cloud-user
        log.info("currentAppName : {}", currentAppName);
        // com.alibaba.cloud.nacos.NacosServiceInstance@6e10c0ea
        log.info("ServiceInstance : {}", serviceInstance);
        // http://192.168.8.106:8091/user/echo/b0x0-cloud-user
        log.info("request url: {}", url);
        return restTemplate.getForObject(url, R.class);
    }


    @Value("${spring.cloud.nacos.discovery.enabled}")
    private Boolean nacosDiscoveryEnabled;

    @GetMapping(value = "/nacos/enabled")
    public String nacosDiscoveryEnabled() {
        log.info("nacosDiscoveryEnabled: {}", nacosDiscoveryEnabled);
        return "spring.cloud.nacos.discovery.enabled :" + nacosDiscoveryEnabled;
    }
}
