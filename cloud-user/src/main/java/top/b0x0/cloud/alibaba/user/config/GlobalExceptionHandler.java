package top.b0x0.cloud.alibaba.user.config;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.b0x0.cloud.alibaba.common.exception.BusinessMsgEnum;
import top.b0x0.cloud.alibaba.common.vo.R;

/**
 * @author TANG
 * @since 2021-08-09
 * @since JDK1.8
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 替换默认流控提示内容:Blocked by sentinel(flow limiting)
     * 流控规则异常处理
     * 如果 @SentinelResource中定义了 blockHandler 和 faidback 属性，则此处不会捕获，以为 blockHandler 和 failback的优先级最高。
     * 限流处理、法一
     */
    @ExceptionHandler({FlowException.class})
    public R handleRRException(FlowException e) {
        e.printStackTrace();
        return R.fail(BusinessMsgEnum.SENTINEL_FLOW_LIMITING_EXCEPTION.getCode(), BusinessMsgEnum.SENTINEL_FLOW_LIMITING_EXCEPTION.getMessage());
    }

    /**
     * 服务降级异常处理
     */
    @ExceptionHandler({DegradeException.class})
    public R handleRRException(DegradeException e) {
        e.printStackTrace();
        return R.fail(BusinessMsgEnum.SENTINEL_DEGRADE_EXCEPTION.getCode(), BusinessMsgEnum.SENTINEL_DEGRADE_EXCEPTION.getMessage());
    }

    /**
     * 处理流量控制异常处理
     */
    @ExceptionHandler({AuthorityException.class})
    public R handleRRException(AuthorityException e) {
        e.printStackTrace();
        return R.fail(BusinessMsgEnum.SENTINEL_AUTHORITY_EXCEPTION.getCode(), BusinessMsgEnum.SENTINEL_AUTHORITY_EXCEPTION.getMessage());
    }

    /**
     * 处理流量控制异常处理
     */
    @ExceptionHandler({ParamFlowException.class})
    public R handleRRException(ParamFlowException e) {
        e.printStackTrace();
        return R.fail(BusinessMsgEnum.SENTINEL_PARAM_FLOW_EXCEPTION.getCode(), BusinessMsgEnum.SENTINEL_PARAM_FLOW_EXCEPTION.getMessage());
    }

    /**
     * 处理流量控制异常处理
     */
    @ExceptionHandler({SystemBlockException.class})
    public R handleRRException(SystemBlockException e) {
        e.printStackTrace();
        return R.fail(BusinessMsgEnum.SENTINEL_SYSTEM_BLOCK_EXCEPTION.getCode(), BusinessMsgEnum.SENTINEL_SYSTEM_BLOCK_EXCEPTION.getMessage());
    }
}
