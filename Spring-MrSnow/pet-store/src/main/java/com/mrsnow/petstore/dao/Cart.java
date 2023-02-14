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
 * 购物车
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_cart")
public class Cart extends Model<Cart> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(BigDecimal amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Cart{" +
        "id=" + id +
        ", isAccount=" + isAccount +
        ", goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", goodsNum=" + goodsNum +
        ", amountMoney=" + amountMoney +
        ", userId=" + userId +
        "}";
    }
}
