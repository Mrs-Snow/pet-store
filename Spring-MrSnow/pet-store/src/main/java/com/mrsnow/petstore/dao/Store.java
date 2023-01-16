package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
    private Long managerId;

    /**
     * 商店名
     */
    private String storeName;

    /**
     * 是否营业
     */
    private String isOpenning;

    /**
     * 所在地区
     */
    private String storePosition;

    /**
     * 管理员姓名
     */
    private String managerName;

    /**
     * 优惠活动id
     */
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

    public String getIsOpenning() {
        return isOpenning;
    }

    public void setIsOpenning(String isOpenning) {
        this.isOpenning = isOpenning;
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
        ", isOpenning=" + isOpenning +
        ", storePosition=" + storePosition +
        ", managerName=" + managerName +
        ", preferentialId=" + preferentialId +
        "}";
    }
}
