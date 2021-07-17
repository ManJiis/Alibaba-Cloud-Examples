package top.b0x0.cloud.alibaba.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.b0x0.cloud.alibaba.common.exception.BusinessMsgEnum;

/**
 * 统一返回对象
 *
 * @author ManJiis
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class R {

    private Object data;
    private Integer code;
    private String message;

    /********************** 成功模板 **********************/
    public static R ok() {
        R response = new R();
        response.setCode(200).setMessage("操作成功");
        return response;
    }

    public static R ok(Integer code, String message) {
        R response = new R();
        response.setCode(code).setMessage(message);
        return response;
    }

    public static R ok(Object data) {
        return new R(data, 200, "操作成功！");
    }

    public static R ok(Object data, Integer code, String message) {
        return new R(data, code, message);
    }

    /********************** 失败模板 **********************/
    public static R fail(String message) {
        R response = new R();
        response.setCode(400).setMessage(message);
        return response;
    }

    public static R fail(Integer code, String message) {
        R response = new R();
        response.setCode(code).setMessage(message);
        return response;
    }

    public static R fail(Object data, Integer code, String msg) {
        return new R(data, code, msg);
    }

    /**
     * 使用自定义异常作为参数传递状态码和提示信息
     *
     * @param msgEnum BusinessMsgEnum
     */
    public static R fail(BusinessMsgEnum msgEnum) {
        return new R().setCode(msgEnum.getCode()).setMessage(msgEnum.getMessage());
    }

}
