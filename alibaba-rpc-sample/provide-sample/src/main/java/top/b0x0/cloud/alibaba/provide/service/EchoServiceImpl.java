package top.b0x0.cloud.alibaba.provide.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import top.b0x0.cloud.alibaba.common.api.ClientEchoService;
import top.b0x0.cloud.alibaba.common.api.EchoService;

@DubboService
public class EchoServiceImpl implements EchoService {

    @DubboReference
    ClientEchoService clientEchoService;

    @Override
    public String echo(String message) {
        String s = "[provide echo] Hello, " + message;
        String echo = clientEchoService.echo(message);
        return s + "---" + echo;
    }

}