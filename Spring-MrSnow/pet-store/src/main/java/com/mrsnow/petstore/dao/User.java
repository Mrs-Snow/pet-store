package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_user")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "ID", type = IdType.ASSIGN_ID)
      @TableField(value = "ID", condition = SqlCondition.EQUAL)

    private Long id;

    /**
     * 用户名称
     */
    @TableField(value = "USER_NAME", condition = SqlCondition.LIKE)
    private String userName;

    /**
     * 密码
     */
    @TableField(value = "PASSWORD", condition = SqlCondition.LIKE)
    private String password;

    /**
     * 是否管理员
     */
    @TableField(value = "IS_ROOT", condition = SqlCondition.LIKE)
    private String isRoot;

    /**
     * 手机号
     */
    @TableField(value = "MOBILE", condition = SqlCondition.LIKE)
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "EMAIL", condition = SqlCondition.LIKE)
    private String email;

    /**
     * 收货地址id
     */
    @TableField(value = "DELIVERY_ADDRESS_ID", condition = SqlCondition.EQUAL)
    private Long deliveryAddressId;

    /**
     * 身份
     */
    @TableField(value = "USER_ROLE", condition = SqlCondition.LIKE)
    private String userRole;

    /**
     * 商店id
     */
    @TableField(value = "STORE_ID", condition = SqlCondition.EQUAL)
    private Long storeId;

    /**
     * 商店名
     */
    @TableField(value = "STORE_NAME", condition = SqlCondition.LIKE)
    private String storeName;

    /**
     * 昵称
     */
    @TableField(value = "NICK_NAME", condition = SqlCondition.LIKE)
    private String nickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isRoot='" + isRoot + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", deliveryAddressId=" + deliveryAddressId +
                ", userRole='" + userRole + '\'' +
                ", storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
