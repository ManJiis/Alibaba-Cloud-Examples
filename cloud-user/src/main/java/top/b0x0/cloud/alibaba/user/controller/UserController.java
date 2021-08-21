package top.b0x0.cloud.alibaba.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.api.IUserService;
import top.b0x0.cloud.alibaba.common.vo.R;
import top.b0x0.cloud.alibaba.common.vo.res.UserRes;

import java.util.List;

/**
 * @author ManJiis
 * @date 2021-08-5
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //    @DubboReference(version = "${service.version}")
    @Autowired
    private IUserService userService;

    @Value("#{'${b0x0.user.defaultUserList}'.split(',')}")
    private List<String> defaultUserList;


    @GetMapping("/findUser")
    public R findUser() {
        UserRes userRes = userService.findUser();
        return R.ok(userRes);
    }

    @GetMapping("/{name}")
    public R hello(@PathVariable String name) {
        String sayHello = userService.sayHello(name);
        return R.ok(sayHello);
    }

    @GetMapping("/list")
    public R list() {
        return R.ok(defaultUserList);
    }
}
