package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_favorite")
public class Favorite extends Model<Favorite> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
      @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 商店id
     */
    @TableField(value = "STORE_ID",condition = SqlCondition.LIKE)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long storeId;

    /**
     * 商店名称
     */
    @TableField(value = "STORE_NAME",condition = SqlCondition.LIKE)
    private String storeName;

    /**
     * 商品名称
     */
    @TableField(value = "GOODS_NAME",condition = SqlCondition.LIKE)
    private String goodsName;

    /**
     * 商品id
     */
    @TableField(value = "GOODS_ID",condition = SqlCondition.LIKE)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Favorite{" +
        "id=" + id +
        ", storeId=" + storeId +
        ", storeName=" + storeName +
        ", goodsName=" + goodsName +
        ", goodsId=" + goodsId +
        "}";
    }
}
