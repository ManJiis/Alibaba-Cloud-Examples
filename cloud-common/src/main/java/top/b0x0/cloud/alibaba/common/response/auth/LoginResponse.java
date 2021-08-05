package top.b0x0.cloud.alibaba.common.response.auth;

import lombok.Data;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
@Data
public class LoginResponse {

    private String token;

    private String refreshToken;

    private String userId;

    private String username;
}
