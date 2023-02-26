package com.mrsnow.petstore.dao;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_order")
@Data
@ToString
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 下单日期
     */
    @TableField(value = "ORDER_TIME",condition = SqlCondition.LIKE)
    private LocalDate orderTime;

    /**
     * 状态
     */
    @TableField(value = "STATUS",condition = SqlCondition.LIKE)
    private String status;

    /**
     * 金额
     */
    @TableField(value = "AMOUNT",condition = SqlCondition.LIKE)
    private BigDecimal amount;

    /**
     * 购物车id
     */
    @TableField(value = "CART_ID",condition = SqlCondition.LIKE)
    private Long cartId;

    /**
     * 快递单号
     */
    @TableField(value = "EXPRESS_NO",condition = SqlCondition.LIKE)
    private String expressNo;

    /**
     * 订单编号
     */
    @TableField(value = "ORDER_NO",condition = SqlCondition.LIKE)
    private String orderNo;

    /**
     * 买家id
     */
    @TableField(value = "USER_ID",condition = SqlCondition.EQUAL)
    private Long userId;

    /**
     * 卖家id
     */
    @TableField(value = "STORE_ID",condition = SqlCondition.EQUAL)
    private Long storeId;

    /**
     * 商品id
     */
    @TableField(value = "GOODS_ID",condition = SqlCondition.EQUAL)
    private Long goodsId;

    @TableField(exist = false)
    private String remark;

    @TableField(exist = false)
    private Goods goodsDetail;

}
