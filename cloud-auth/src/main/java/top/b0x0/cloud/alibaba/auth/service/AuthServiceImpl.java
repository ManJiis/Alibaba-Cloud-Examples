package top.b0x0.cloud.alibaba.auth.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.b0x0.cloud.alibaba.api.auth.IAuthService;

/**
 * @author TANG
 * @since 2021-08-05
 * @since JDK1.8
 */
@Service(version = "${service.version}")
@Component
public class AuthServiceImpl implements IAuthService {

    @Override
    public Boolean isDefaultUser(String username) {
        return "admin".equals(username);
    }
}
