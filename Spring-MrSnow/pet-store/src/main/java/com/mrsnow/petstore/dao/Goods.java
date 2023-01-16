package com.mrsnow.petstore.dao;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Goods extends Model<Goods> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品类名
     */
    private String className;

    /**
     * 商店id
     */
    private Long storeId;

    /**
     * 商店名称
     */
    private String storeName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 优惠活动id
     */
    private Long preferentialId;

    /**
     * 库存数量
     */
    private Integer inventoryNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getPreferentialId() {
        return preferentialId;
    }

    public void setPreferentialId(Long preferentialId) {
        this.preferentialId = preferentialId;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Goods{" +
        "id=" + id +
        ", className=" + className +
        ", storeId=" + storeId +
        ", storeName=" + storeName +
        ", price=" + price +
        ", preferentialId=" + preferentialId +
        ", inventoryNum=" + inventoryNum +
        "}";
    }
}
