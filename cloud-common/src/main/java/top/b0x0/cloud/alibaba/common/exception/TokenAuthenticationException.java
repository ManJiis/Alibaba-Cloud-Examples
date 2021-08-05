package top.b0x0.cloud.alibaba.common.exception;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
public class TokenAuthenticationException extends RuntimeException {

    public TokenAuthenticationException() {
        super();
    }

    public TokenAuthenticationException(int code, String message) {
        super(code + message);
    }
}
