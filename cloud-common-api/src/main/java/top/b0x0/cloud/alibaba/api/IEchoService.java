package top.b0x0.cloud.alibaba.api;

/**
 * @author ManJiis
 * @date 2021-07-16
 * @since jdk1.8
 */
public interface IEchoService {

    default String userThenAuthSayHello(String param) {
        return null;
    }

    ;

    String sayHello(String param);

    String bonjour(String name);
}
