package top.b0x0.cloud.alibaba.auth.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import top.b0x0.cloud.alibaba.api.auth.IAuthService;

/**
 * @author TANG
 * @since 2021-08-05
 * @since JDK1.8
 */
@DubboService(version = "${service.version}")
@Component
public class AuthServiceImpl implements IAuthService {

    @Override
    public Boolean isDefaultUser(String username) {
        return "admin".equals(username);
    }
}
