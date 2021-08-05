package top.b0x0.cloud.alibaba.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.b0x0.cloud.alibaba.common.util.ServiceList;
import top.b0x0.cloud.alibaba.common.vo.R;

/**
 * b0x0-provider1
 *
 * @author TANG
 * @since 2021-07-19
 * @since JDK1.8
 */
@FeignClient(value = ServiceList.PROVIDER_USER, fallback = UserClient.UserHystrixClientFallback.class)
@RequestMapping("/user")
public interface UserClient {

    @GetMapping(value = "/echo1/{string}/{string2}")
    R sayHello(@PathVariable("string") String name, @PathVariable("string2") String string2);

    @GetMapping(value = "/bonjour")
    R bonjour(@RequestParam("name") String name);

//    @Component
    class UserHystrixClientFallback implements UserClient {
        @Override
        public R sayHello(String name, String string2) {
            return R.fail("UserClient 开小差了..");
        }

        @Override
        public R bonjour(String name) {
            return R.fail("UserClient 开小差了..");
        }
    }
}
