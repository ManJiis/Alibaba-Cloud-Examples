package top.b0x0.cloud.alibaba.auth.service;

import org.apache.dubbo.config.annotation.DubboService;
import top.b0x0.cloud.alibaba.api.UserService;

/**
 * @author ManJiis
 */
@DubboService(version = "${service.version}")
public class UserServiceImpl implements UserService {

    @Override
    public String sayHello(String name) {
        return "b0x0-cloud-auth say hello -->" + name;
    }
}
