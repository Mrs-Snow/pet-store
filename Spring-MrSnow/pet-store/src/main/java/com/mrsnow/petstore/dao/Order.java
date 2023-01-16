package com.mrsnow.petstore.dao;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 下单日期
     */
    private LocalDate orderTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 购物车id
     */
    private Long cartId;

    /**
     * 快递单号
     */
    private String expressNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 买家id
     */
    private Long userId;

    /**
     * 卖家id
     */
    private Long storeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
        "id=" + id +
        ", orderTime=" + orderTime +
        ", status=" + status +
        ", amount=" + amount +
        ", cartId=" + cartId +
        ", expressNo=" + expressNo +
        ", orderNo=" + orderNo +
        ", userId=" + userId +
        ", storeId=" + storeId +
        "}";
    }
}
