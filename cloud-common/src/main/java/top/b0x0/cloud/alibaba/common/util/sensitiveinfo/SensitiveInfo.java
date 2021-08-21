package top.b0x0.cloud.alibaba.common.util.sensitiveinfo;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 加载bean的字段上面 只支持String类型的字段 实现接口数据脱密
 *
 * @author TANG
 * @date 2021-07-12
 * @since JDK 1.8
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

    SensitiveTypeEnum value();

}