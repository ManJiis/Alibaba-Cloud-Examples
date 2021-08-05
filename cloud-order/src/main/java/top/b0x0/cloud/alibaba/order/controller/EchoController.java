package top.b0x0.cloud.alibaba.order.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.api.IEchoService;
import top.b0x0.cloud.alibaba.api.auth.IAuthService;
import top.b0x0.cloud.alibaba.common.vo.R;
import top.b0x0.cloud.alibaba.order.client.AuthClient;
import top.b0x0.cloud.alibaba.order.client.UserClient;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @Reference(version = "${service.version}", group = "b0x0-cloud-user")
    private IEchoService userEchoService;

    @Reference(version = "${service.version}", group = "b0x0-cloud-auth")
    private IEchoService authEchoService;

    @Reference(version = "${service.version}")
    private IAuthService authService;

    @Autowired
    AuthClient authClient;
    @Autowired
    UserClient userClient;

    @GetMapping("isDefaultUser")
    public R isDefaultUser(String name) {
        System.out.println("authService = " + authService);
        return R.ok(authService.isDefaultUser(name));
    }

    @GetMapping("user")
    public R userEcho(String name) {
        System.out.println("userEchoService = " + userEchoService);
        return R.ok(userEchoService.sayHello(name));
    }

    @GetMapping("auth")
    public R authEcho(String name) {
        System.out.println("authEchoService = " + authEchoService);
        return R.ok(authEchoService.sayHello(name));
    }

    @GetMapping("client/user")
    public R userClientEcho(@RequestParam("name") String name, @RequestParam("name2") String name2) {
        return userClient.sayHello(name, name2);
    }

    @GetMapping("client/auth")
    public R authClientEcho(String name) {
        return authClient.sayHello(name);
    }
}
