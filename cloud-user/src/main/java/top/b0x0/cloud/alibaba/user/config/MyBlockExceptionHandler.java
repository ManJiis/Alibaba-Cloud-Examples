package top.b0x0.cloud.alibaba.user.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.b0x0.cloud.alibaba.common.vo.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ManJiis
 * @since 2021-08-09
 * @since JDK 1.8
 */
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    public MyBlockExceptionHandler() {
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException ex) throws Exception {
        if (FlowException.isBlockException(ex)) {
            // 触发流控规则
            writer(httpServletRequest, httpServletResponse, R.fail("触发流控规则..."));
        }
    }

    public static boolean writer(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, R result) {
        PrintWriter writer = null;
        String originHeader = httpServletRequest.getHeader("Origin");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", originHeader);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Vary", "Origin");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(result);
            writer = httpServletResponse.getWriter();
            writer.append(jsonObject.toString());
        } catch (IOException e) {
            System.out.println("response error" + e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return false;
    }
}
