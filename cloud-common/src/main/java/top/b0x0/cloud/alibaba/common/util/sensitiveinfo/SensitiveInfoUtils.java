package top.b0x0.cloud.alibaba.common.util.sensitiveinfo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * @author TANG
 * @date 2021-07-12
 * @since JDK 1.8
 */
public class SensitiveInfoUtils {
    private static final Logger log = LoggerFactory.getLogger(SensitiveInfoUtils.class);

    /**
     * 返回一个处理过敏感信息的新对象,不影响原对象数据
     *
     * @param source /
     * @return /
     */
    public static Object noSensitiveObject(Object source) {
        if (source == null) {
            return null;
        }
        Class<?> aClass = source.getClass();
        try {
            Object newInstance = aClass.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            Class<?> newInstanceClass = newInstance.getClass();
            Field[] newInstanceClassDeclaredFields = newInstanceClass.getDeclaredFields();
            for (Field field : newInstanceClassDeclaredFields) {
                field.setAccessible(true);
                SensitiveInfoType sensitiveInfo = field.getAnnotation(SensitiveInfoType.class);
                if (sensitiveInfo != null) {
                    SensitiveTypeEnum value = sensitiveInfo.value();
                    Object val = field.get(newInstance);
                    if (val == null) {
                        continue;
                    }
                    String valString = val.toString();
                    String newValString = null;
                    switch (value) {
                        case CHINESE_NAME:
                            newValString = SensitiveInfoUtils.chineseName(valString);
                            break;
                        case ID_CARD:
                            newValString = SensitiveInfoUtils.idCardNum(valString);
                            break;
                        case MOBILE_PHONE:
                            newValString = SensitiveInfoUtils.mobilePhone(valString);
                            break;
                        case BANK_CARD:
                            newValString = SensitiveInfoUtils.bankCard(valString);
                            break;
                        default:
                    }
                    field.set(newInstance, newValString);
                }
            }
            return newInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            log.error("RemoveSensitiveInfoUtils error:{}", e.getMessage());
        }
        return null;
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     */
    public static String chineseName(final String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        final String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     */
    public static String chineseName(final String familyName, final String givenName) {
        if (StringUtils.isBlank(familyName) || StringUtils.isBlank(givenName)) {
            return "";
        }
        return chineseName(familyName + givenName);
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     */
    public static String idCardNum(final String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }

        return StringUtils.left(id, 6).concat(StringUtils
                .removeStart(StringUtils.leftPad(StringUtils.right(id, 4), StringUtils.length(id), "*"),
                        "***"));
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     */
    public static String mobilePhone(final String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(StringUtils
                .removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"),
                        "***"));

    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     */
    public static String bankCard(final String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils.left(cardNum, 4).concat(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"),
                "****"));
    }

    public static void main(String[] args) {
        String cardNum = "1069879467867897856";
        System.out.println(bankCard("1069*********7856"));
        System.out.println(StringUtils.removeStart(
                StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"),
                "******"));
    }

}