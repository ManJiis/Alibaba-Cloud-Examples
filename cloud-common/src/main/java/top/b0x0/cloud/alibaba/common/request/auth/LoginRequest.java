package top.b0x0.cloud.alibaba.common.request.auth;

import lombok.Data;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
