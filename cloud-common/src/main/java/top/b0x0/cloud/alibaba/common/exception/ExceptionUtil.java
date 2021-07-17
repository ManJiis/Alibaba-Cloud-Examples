package top.b0x0.cloud.alibaba.common.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author ManJiis
 */
public final class ExceptionUtil {

    public static void exceptionHandler(BlockException ex) {
        // Handler method that handles BlockException when blocked.
        // The method parameter list should match original method, with the last additional
        // parameter with type BlockException. The return type should be same as the original method.
        // The block handler method should be located in the same class with original method by default.
        // If you want to use method in other classes, you can set the blockHandlerClass
        // with corresponding Class (Note the method in other classes must be static).
        ex.printStackTrace();
        System.out.println("Oops: " + ex.getClass().getCanonicalName());
    }
}