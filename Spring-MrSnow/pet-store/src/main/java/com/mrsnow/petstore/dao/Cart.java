package com.mrsnow.petstore.dao;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_cart")
@ToString(callSuper = true)
@Data
public class Cart extends Model<Cart> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(type = IdType.ASSIGN_ID)
      @TableField(value = "id", condition = SqlCondition.EQUAL)
      @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 是否结算
     */
    @TableField(value = "IS_ACCOUNT", condition = SqlCondition.LIKE)
    private String isAccount;

    /**
     * 商品id
     */
    @TableField(value = "GOODS_ID", condition = SqlCondition.LIKE)
    private Long goodsId;

    /**
     * 商品名字
     */
    @TableField(value = "GOODS_NAME", condition = SqlCondition.LIKE)
    private String goodsName;

    /**
     * 商品数量
     */
    @TableField(value = "GOODS_NUM", condition = SqlCondition.LIKE)
    private Integer goodsNum;

    /**
     * 总金额
     */
    @TableField(value = "AMOUNT_MONEY", condition = SqlCondition.LIKE)
    private BigDecimal amountMoney;

    /**
     * 用户id
     */
    @TableField(value = "USER_ID", condition = SqlCondition.LIKE)
    private Long userId;

    /**
     * 用户id
     */
    @TableField(value = "STORE_ID", condition = SqlCondition.EQUAL)
    private Long storeId;

    @TableField(exist = false)
    private Goods goodsDetail;

}
