package top.b0x0.cloud.alibaba.common.pojo;

import lombok.Data;

/**
 * @author ManJiis
 * @date 2020/10/15
 */
@Data
public class ProductOrder {

    private int id;

    private double amount;

    private int userId;

    private String userName;

    private int productId;
}
