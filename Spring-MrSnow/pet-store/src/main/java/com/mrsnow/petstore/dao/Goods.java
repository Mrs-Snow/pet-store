package com.mrsnow.petstore.dao;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品类名
     */
    @TableField(value = "CLASS_NAME", condition = SqlCondition.LIKE)
    private String className;

    /**
     * 商店id
     */
    @TableField(value = "STORE_ID", condition = SqlCondition.EQUAL)
    private Long storeId;

    /**
     * 商店名称
     */
    @TableField(value = "STORE_NAME", condition = SqlCondition.LIKE)
    private String storeName;

    /**
     * 价格
     */
    @TableField(value = "PRICE", condition = SqlCondition.LIKE)
    private BigDecimal price;

    /**
     * 优惠活动id
     */
    @TableField(value = "PREFERENTIAL_ID", condition = SqlCondition.LIKE)
    private Long preferentialId;

    /**
     * 库存数量
     */
    @TableField(value = "INVENTORY_NUM", condition = SqlCondition.LIKE)
    private Integer inventoryNum;

    /**
     * 商品名称
     */
    @TableField(value = "GOODS_NAME", condition = SqlCondition.LIKE)
    private String goodsName;

    /**
     * 商品名称
     */
    @TableField(value = "GOODS_PIC", condition = SqlCondition.EQUAL)
    private String goodsPic;

    /**
     * 优惠活动
     */
    @TableField(exist = false)
    private Preferential preferential;
}

