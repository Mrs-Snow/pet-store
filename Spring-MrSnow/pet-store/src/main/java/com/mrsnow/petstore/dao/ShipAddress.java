package com.mrsnow.petstore.dao;

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
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 发货人
     */
    private String consigner;

    /**
     * 发货人手机号
     */
    private String consignerPhone;

    /**
     * 发货人地址
     */
    private String consignerAddress;

    /**
     * 商店id
     */
    private String storeId;

    /**
     * 商店名称
     */
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
        return consignerPhone;
    }

    public void setConsignerPhone(String consignerPhone) {
        this.consignerPhone = consignerPhone;
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
        ", consignerPhone=" + consignerPhone +
        ", consignerAddress=" + consignerAddress +
        ", storeId=" + storeId +
        ", storeName=" + storeName +
        "}";
    }
}
