package top.b0x0.cloud.alibaba.common.vo.res;

import lombok.Data;
import lombok.experimental.Accessors;
import top.b0x0.cloud.alibaba.common.util.sensitiveinfo.SensitiveInfo;
import top.b0x0.cloud.alibaba.common.util.sensitiveinfo.SensitiveTypeEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ManJiis
 * @since 2021-08-21
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
public class UserRes implements Serializable {
    private static final long serialVersionUID = -5231662552427409413L;

    @SensitiveInfo(SensitiveTypeEnum.CHINESE_NAME)
    private String username;

    private String nickName;

    @SensitiveInfo(SensitiveTypeEnum.MOBILE_PHONE)
    private String phone;

    @SensitiveInfo(SensitiveTypeEnum.ID_CARD)
    private String idCard;

    @SensitiveInfo(SensitiveTypeEnum.BANK_CARD)
    private String bankCard;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
