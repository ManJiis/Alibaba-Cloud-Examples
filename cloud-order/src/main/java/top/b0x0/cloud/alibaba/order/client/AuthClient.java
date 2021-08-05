package top.b0x0.cloud.alibaba.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.b0x0.cloud.alibaba.common.util.ServiceList;
import top.b0x0.cloud.alibaba.common.vo.R;

/**
 * b0x0-provider2
 *
 * @author TANG
 * @since 2021-07-19
 * @since JDK1.8
 */
@FeignClient(value = ServiceList.PROVIDER_AUTH, fallback = AuthClient.AuthHystrixClientFallback.class)
@RequestMapping("/auth")
public interface AuthClient {

    @GetMapping("/echo/userSayHello")
    R sayHello(@RequestParam("name") String name);

    @GetMapping("/bonjour")
    R bonjour(@RequestParam("name") String name);

//    @Component // No fallback instance of type class top.b0x0.cloud.alibaba.order.client.AuthClient$HystrixClientFallback found for feign client b0x0-cloud-auth
    class AuthHystrixClientFallback implements AuthClient {
        @Override
        public R sayHello(String name) {
            return R.fail("AuthClient 开小差了..");
        }

        @Override
        public R bonjour(String name) {
            return R.fail("AuthClient 开小差了..");
        }
    }
}
