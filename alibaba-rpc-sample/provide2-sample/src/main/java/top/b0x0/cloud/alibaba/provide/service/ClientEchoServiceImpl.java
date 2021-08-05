package top.b0x0.cloud.alibaba.provide.service;

import org.apache.dubbo.config.annotation.DubboService;
import top.b0x0.cloud.alibaba.common.api.ClientEchoService;

@DubboService
public class ClientEchoServiceImpl implements ClientEchoService {

    @Override
    public String echo(String message) {
        String msg = "[provide2 echo] Hello, " + message;
        System.out.println("provide2 echo = " + msg);
        return msg;
    }

}