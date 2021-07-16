package top.b0x0.cloud.alibaba.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import top.b0x0.cloud.alibaba.api.EchoService;

/**
 * @author TANG
 * @date 2021-07-16
 * @since 1.8
 */
@DubboService(version = "${service.version}")
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String param) {
        return param;
    }
}
