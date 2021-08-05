package top.b0x0.cloud.alibaba.common.util.constant;

import java.util.regex.Pattern;

/**
 * 常量类
 *
 * @author TANG
 * @since 2020-12-05
 */
public class CommonConstants {

    private CommonConstants() {
    }

    /**
     * 手机号
     */
    public static Pattern REX_PHONE = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    /**
     * 邮箱 只允许英文字母、数字、下划线、英文句号、以及中划线组成
     */
    public static Pattern REX_EMAIL = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    /**
     * 验证码类型
     */
    public static final String CODE_CALCULATE = "calculate";
    public static final String CODE_SMS = "sms";
    public static final String CODE_DYNAMIC = "dynamic";

    /**
     * 账号默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 用户状态  0：禁用   1：正常  2:冻结
     */
    public static final int SYS_USER_STATUS_PROHIBIT = 0;
    public static final int SYS_USER_STATUS_NORMAL = 1;
    public static final int SYS_USER_STATUS_FROZEN = 2;


    /**
     * exception msg
     */
    public static final String REQUEST_MSG_BAD = "客户端请求参数错误";
    public static final String REQUEST_MSG_MISSING_PARAMETERS = "缺少必要的请求参数";
}
