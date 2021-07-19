package top.b0x0.cloud.alibaba.web.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.api.UserService;
import top.b0x0.cloud.alibaba.common.vo.R;

@RestController
public class UserController {

    @DubboReference(version = "${service.version}")
    private UserService userService;


    @GetMapping("/user/{name}")
    public R hello(@PathVariable String name) {
        String sayHello = userService.sayHello(name);
        return R.fail(sayHello);
    }
}
