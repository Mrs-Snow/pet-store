package com.mrsnow.petstore.dao;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 优惠表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_preferential")
public class Preferential extends Model<Preferential> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 优惠价格
     */
    private BigDecimal preferentialPrice;

    /**
     * 价钱阈值
     */
    private BigDecimal priceValue;

    /**
     * 商店id
     */
    private Long storeId;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 数量阈值
     */
    private Integer countValue;

    /**
     * 描述
     */
    private String comment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(BigDecimal preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(BigDecimal priceValue) {
        this.priceValue = priceValue;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getCountValue() {
        return countValue;
    }

    public void setCountValue(Integer countValue) {
        this.countValue = countValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Preferential{" +
        "id=" + id +
        ", preferentialPrice=" + preferentialPrice +
        ", priceValue=" + priceValue +
        ", storeId=" + storeId +
        ", discount=" + discount +
        ", countValue=" + countValue +
        ", comment=" + comment +
        "}";
    }
}
