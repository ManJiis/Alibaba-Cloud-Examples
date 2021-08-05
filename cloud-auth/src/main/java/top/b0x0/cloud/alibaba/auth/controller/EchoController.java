package top.b0x0.cloud.alibaba.auth.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.api.IEchoService;
import top.b0x0.cloud.alibaba.common.vo.R;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/echo")
public class EchoController {

    @Reference(version = "${service.version}", group = "b0x0-cloud-user")
//    @Autowired
    private IEchoService userEchoService;

    //        @Reference(version = "${service.version}", group = "b0x0-cloud-auth")
//    @Autowired
    private IEchoService authEchoService;

    @GetMapping("authSayHello")
    public R authSayHello(@RequestParam("name") String name) {
        return R.ok(authEchoService.sayHello(name));
    }

    @GetMapping("userSayHello")
    public R userSayHello(@RequestParam("name") String name) {
        return R.ok(userEchoService.sayHello(name));
    }
}
