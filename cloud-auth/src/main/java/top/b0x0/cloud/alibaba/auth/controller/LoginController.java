package top.b0x0.cloud.alibaba.auth.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.b0x0.cloud.alibaba.common.pojo.SysUser;
import top.b0x0.cloud.alibaba.common.request.auth.LoginRequest;
import top.b0x0.cloud.alibaba.common.request.auth.RefreshRequest;
import top.b0x0.cloud.alibaba.common.response.auth.LoginResponse;
import top.b0x0.cloud.alibaba.common.util.JWTUtil;
import top.b0x0.cloud.alibaba.common.util.constant.AuthRedisKey;
import top.b0x0.cloud.alibaba.common.util.properties.PropertiesLoader;
import top.b0x0.cloud.alibaba.common.vo.ResponseCodeEnum;
import top.b0x0.cloud.alibaba.common.vo.ResponseResult;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ManJiis
 * @date 2020/11/10
 */
@RestController
@RequestMapping("/auth")
public class LoginController {


    @Value("${secretKey:123456}")
    private String secretKey;

    private final Set<SysUser> defaultUserList = new HashSet<>();

    @PostConstruct
    void init() {
        Properties properties = PropertiesLoader.getProperties("default-user");
        JSONArray jsonArray = (JSONArray) JSONObject.parse(properties.get("default.users").toString());
        for (Object o : jsonArray) {
            SysUser sysUser = new SysUser();
            String[] split = o.toString().split(":");
            sysUser.setUserId(split[0]);
            sysUser.setUsername(split[1]);
            sysUser.setPassword(split[2]);
            defaultUserList.add(sysUser);
        }
        System.out.println("defaultUserList = " + defaultUserList);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    final static String TOKEN = "token";

    final static String REFRESH_TOKEN = "refreshToken";

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.error(ResponseCodeEnum.PARAMETER_ILLEGAL.getCode(), ResponseCodeEnum.PARAMETER_ILLEGAL.getMessage());
        }
        String username = request.getUsername();
        String password = request.getPassword();

        for (SysUser sysUser : defaultUserList) {
            if (sysUser.getUsername().equals(username) && sysUser.getPassword().equals(password)) {
                String userId = sysUser.getUserId();

                //  生成Token
                String token = JWTUtil.generateToken(userId, username, secretKey);

                //  生成刷新Token
                String refreshToken = UUID.randomUUID().toString().replace("-", "");

                //  放入缓存
                HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();

                String cacheKey = String.format(AuthRedisKey.KEY_LOGIN, userId);
                hashOperations.put(cacheKey, TOKEN, token);
                hashOperations.put(cacheKey, REFRESH_TOKEN, refreshToken);
                stringRedisTemplate.expire(cacheKey, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setRefreshToken(refreshToken);
                loginResponse.setUserId(userId);
                loginResponse.setUsername(username);

                return ResponseResult.success(loginResponse);
            }
        }
        return ResponseResult.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
    }

    @GetMapping("/logout")
    public ResponseResult logout(@RequestParam("userId") String userId) {
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String key = String.format(AuthRedisKey.KEY_LOGIN, userId);
        hashOperations.delete(key, TOKEN);
        return ResponseResult.success();
    }

    @PostMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestBody @Validated RefreshRequest request, BindingResult bindingResult) {
        String userId = request.getUserId();
        // TODO
        //  通过userId去数据库查到userName
        String userName = request.getUserName();
        String refreshToken = request.getRefreshToken();
        HashOperations<String, String, String> hashOperations = stringRedisTemplate.opsForHash();
        String redisCacheKey = String.format(AuthRedisKey.KEY_LOGIN, userId);
        String originalRefreshToken = hashOperations.get(redisCacheKey, REFRESH_TOKEN);
        if (StringUtils.isBlank(originalRefreshToken) || !originalRefreshToken.equals(refreshToken)) {
            return ResponseResult.error(ResponseCodeEnum.REFRESH_TOKEN_INVALID.getCode(), ResponseCodeEnum.REFRESH_TOKEN_INVALID.getMessage());
        }

        //  生成新token
        String newToken = JWTUtil.generateToken(userId, userName, secretKey);
        hashOperations.put(redisCacheKey, TOKEN, newToken);
        stringRedisTemplate.expire(userId, JWTUtil.TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);

        return ResponseResult.success(newToken);
    }
}
