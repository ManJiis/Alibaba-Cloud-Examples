package top.b0x0.cloud.alibaba.user.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.b0x0.cloud.alibaba.api.EchoService;

/**
 * @author ManJiis
 * @since 2021-07-16
 * @since 1.8
 */
@DubboService(version = "${service.version}", group = "b0x0-cloud-user")
@Component
public class EchoServiceImpl implements EchoService {
    private static final Logger log = LoggerFactory.getLogger(EchoServiceImpl.class);

    /**
     * 原函数
     *
     * @param param /
     * @return /
     */
    @Override
//    @SentinelResource(value = "sayHello", blockHandler = "exceptionHandler", blockHandlerClass = ExceptionUtil.class, fallback = "helloFallback", defaultFallback = "defaultFallback", exceptionsToIgnore = {})
//    @SentinelResource(value = "sayHello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String sayHello(String param) {
        log.info("provider1 = {}", param);
        if ("error".equals(param)) {
            throw new RpcException("error oops...");
        }
        return param;
    }

    @Override
    public String bonjour(String name) {
        return null;
    }

    /**
     * Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     *
     * @param s /
     * @return /
     */
    public String helloFallback(String s) {
        return String.format("provide1 --> EchoServiceImpl sayHello %s", s);
    }

//    public String helloFallback(String s, Throwable ex) {
//        // Do some log here.
//        ex.printStackTrace();
//        return "Oops, error occurred at " + s;
//    }

    /**
     * Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     *
     * @param s  /
     * @param ex /
     * @return /
     */
    public String exceptionHandler(String s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;
    }


    public String defaultFallback() {
        log.info("Go to default fallback");
        return "default_fallback";
    }

}
