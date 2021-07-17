package top.b0x0.cloud.alibaba.common.exception;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.json.JSONArray;
import org.apache.dubbo.common.json.JSONObject;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.validation.support.jvalidation.JValidator;
import org.apache.dubbo.validation.Validator;

import javax.validation.*;
import java.util.*;

/**
 * 使用方式:
 * 1. provider端启用(注解方式)
 * Service(validation = "xxx")
 * 2. consumer端启用(注解方式)
 * Reference(validation = "xxx")
 *
 * 链接: https://www.jianshu.com/p/21cd7fa6e930
 * <p>
 * 继承自{@link JValidator}保证了dubbo原生的vidation逻辑。
 * 修改点：
 * 1、捕获ConstraintViolationException异常，将验证未通过异常改为ValidationException，解决dubbo hessian反序列化失败问题
 * 2、格式化验证提示，使用json格式响应
 *
 * @author ManJiis
 * @since 2021-07-18
 */
public class CustomValidator extends JValidator implements Validator {

    private final Class<?> clazz;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CustomValidator(URL url) {
        super(url);
        this.clazz = ReflectUtils.forName(url.getServiceInterface());
    }

    @Override
    public void validate(String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Exception {

        try {
            super.validate(methodName, parameterTypes, arguments);
        } catch (ConstraintViolationException e) {
            throw new ValidationException(constraintMessage(clazz.getName(), methodName, e.getConstraintViolations()));
        }
    }

    private String constraintMessage(String className, String methodName, Set<ConstraintViolation<?>> violations) {
        JSONObject json = new JSONObject();
        json.put("service", className);
        json.put("method", methodName);

        JSONArray details = new JSONArray();
        for (ConstraintViolation violation : violations) {
            JSONObject detail = new JSONObject();
            detail.put("bean", violation.getRootBean().getClass().getName());
            detail.put("property", violation.getPropertyPath().toString());
            detail.put("message", violation.getMessage());
            details.add(detail);
        }
        json.put("details", details);
        return json.toString();
    }
}