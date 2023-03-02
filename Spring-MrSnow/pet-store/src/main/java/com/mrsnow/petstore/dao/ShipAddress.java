package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * <p>
 * 发货地址表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_shipAddress")
public class ShipAddress extends Model<ShipAddress> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 发货人
     */
    @TableField(value = "CONSIGNER",condition = SqlCondition.LIKE)
    private String consigner;

    /**
     * 发货人手机号
     */
    @TableField(value = "CONSIGNER_PHONE",condition = SqlCondition.LIKE)
    private String consignedPhone;

    /**
     * 发货人地址
     */
    @TableField(value = "CONSIGNER_ADDRESS",condition = SqlCondition.LIKE)
    private String consignerAddress;

    /**
     * 商店id
     */
    @TableField(value = "STORE_ID",condition = SqlCondition.LIKE)
    @JsonSerialize(using= ToStringSerializer.class)
    private String storeId;

    /**
     * 商店名称
     */
    @TableField(value = "STORE_NAME",condition = SqlCondition.LIKE)
    private String storeName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsigner() {
        return consigner;
    }

    public void setConsigner(String consigner) {
        this.consigner = consigner;
    }

    public String getConsignerPhone() {
        return consignedPhone;
    }

    public void setConsignerPhone(String consignerPhone) {
        this.consignedPhone = consignerPhone;
    }

    public String getConsignerAddress() {
        return consignerAddress;
    }

    public void setConsignerAddress(String consignerAddress) {
        this.consignerAddress = consignerAddress;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ShipAddress{" +
        "id=" + id +
        ", consigner=" + consigner +
        ", consignerPhone=" + consignedPhone +
        ", consignerAddress=" + consignerAddress +
        ", storeId=" + storeId +
        ", storeName=" + storeName +
        "}";
    }
}
