package top.b0x0.cloud.alibaba.client.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.b0x0.cloud.alibaba.common.api.ClientEchoService;
import top.b0x0.cloud.alibaba.common.api.EchoService;

/**
 * @author TANG
 * @since 2021-08-05
 * @since JDK1.8
 */
@RestController
public class ClientController {

    @DubboReference
    private EchoService echoService;
    @DubboReference
    private ClientEchoService clientEchoService;

    @GetMapping("/client/echo")
    public String echo(String message) {
        return clientEchoService.echo(message);
    }

    @GetMapping("echo")
    public String echo2(String message) {
        return echoService.echo(message);
    }

    @GetMapping("echo3")
    public String echo3(String message) {
        return "client say hello: " + message;
    }
}
