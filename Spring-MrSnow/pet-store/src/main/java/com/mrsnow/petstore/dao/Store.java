package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 商店表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_store")
public class Store extends Model<Store> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 管理员id
     */
    @TableField(value = "MANAGER_ID",condition = SqlCondition.LIKE)
    private Long managerId;

    /**
     * 商店名
     */
    @TableField(value = "STORE_NAME",condition = SqlCondition.LIKE)
    private String storeName;

    /**
     * 是否营业
     */
    @TableField(value = "IS_OPENING",condition = SqlCondition.LIKE)
    private String isOpening;

    /**
     * 所在地区
     */
    @TableField(value = "STORE_POSITION",condition = SqlCondition.LIKE)
    private String storePosition;

    /**
     * 管理员姓名
     */
    @TableField(value = "MANAGER_NAME",condition = SqlCondition.LIKE)
    private String managerName;

    /**
     * 优惠活动id
     */
    @TableField(value = "PREFERENTIAL_ID",condition = SqlCondition.LIKE)
    private Long preferentialId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIsOpening() {
        return isOpening;
    }

    public void setIsOpening(String isOpening) {
        this.isOpening = isOpening;
    }

    public String getStorePosition() {
        return storePosition;
    }

    public void setStorePosition(String storePosition) {
        this.storePosition = storePosition;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getPreferentialId() {
        return preferentialId;
    }

    public void setPreferentialId(Long preferentialId) {
        this.preferentialId = preferentialId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Store{" +
        "id=" + id +
        ", managerId=" + managerId +
        ", storeName=" + storeName +
        ", isOpening=" + isOpening +
        ", storePosition=" + storePosition +
        ", managerName=" + managerName +
        ", preferentialId=" + preferentialId +
        "}";
    }
}
