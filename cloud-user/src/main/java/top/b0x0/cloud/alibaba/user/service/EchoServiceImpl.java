package top.b0x0.cloud.alibaba.user.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.b0x0.cloud.alibaba.api.IEchoService;

/**
 * @author ManJiis
 * @since 2021-07-16
 * @since 1.8
 */
@DubboService(version = "${service.version}", group = "b0x0-cloud-user")
@Component
public class EchoServiceImpl implements IEchoService {
    private static final Logger log = LoggerFactory.getLogger(EchoServiceImpl.class);

    /**
     * 原函数
     *
     * @param param /
     * @return /
     */
    @Override
    //@SentinelResource()
    //@SentinelResource(value = "user#EchoServiceImpl#sayHello",fallback = "sayHelloFallback") //fallback只负责业务异常
    //@SentinelResource(value = "user#EchoServiceImpl#sayHello",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "user#EchoServiceImpl#sayHello", fallback = "sayHelloFallback", blockHandler = "blockHandler")
    public String sayHello(String param) {
        System.out.println("user#EchoServiceImpl#sayHello： " + param);
        if ("error".equals(param)) {
            throw new RpcException("error oops...");
        }
        return param;
    }

    @Override
    @SentinelResource(value = "user#EchoServiceImpl#bonjour", fallback = "defaultFallback")
    public String bonjour(String name) {
        if ("error".equals(name)) {
            throw new RpcException("error oops...");
        }
        return name;
    }

    /**
     * Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     *
     * @param s /
     * @return /
     */
    public String sayHelloFallback(String s) {
        return String.format("user sayHelloFallback() --> EchoServiceImpl sayHello %s", s);
    }

    /**
     * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     *
     * @param s  /
     * @param ex /
     * @return /
     */
    public String blockHandler(String s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }

    public String defaultFallback(String name) {
        log.info("Go to default fallback");
        return "default_fallback";
    }

}
