package top.b0x0.cloud.alibaba.user.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.b0x0.cloud.alibaba.api.IUserService;

/**
 * @author ManJiis
 */
@Service(version = "${service.version}")
@Component
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello(String name) {
        return "b0x0-cloud-user say hello -->" + name;
    }
}
