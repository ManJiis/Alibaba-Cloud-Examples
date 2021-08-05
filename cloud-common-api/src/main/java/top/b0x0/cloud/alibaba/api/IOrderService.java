package top.b0x0.cloud.alibaba.api;


import top.b0x0.cloud.alibaba.common.pojo.ProductOrder;

/**
 * @author ManJiis
 * @date 2020/10/15
 */
public interface IOrderService {

    /**
     * 下单
     *
     * @param productId
     * @param userId
     */
    ProductOrder makeOrder(int productId, int userId);
}
