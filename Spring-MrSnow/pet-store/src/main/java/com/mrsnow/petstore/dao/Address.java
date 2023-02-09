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
 * 收获地址表
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@TableName("pet_address")
public class Address extends Model<Address> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 收货人
     */
    private String 收货人;

    /**
     * 收货人手机号
     */
    private String 收货人手机号;

    /**
     * 收货地址
     */
    private String 收货地址;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String get收货人() {
        return 收货人;
    }

    public void set收货人(String 收货人) {
        this.收货人 = 收货人;
    }

    public String get收货人手机号() {
        return 收货人手机号;
    }

    public void set收货人手机号(String 收货人手机号) {
        this.收货人手机号 = 收货人手机号;
    }

    public String get收货地址() {
        return 收货地址;
    }

    public void set收货地址(String 收货地址) {
        this.收货地址 = 收货地址;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Address{" +
        "id=" + id +
        ", userId=" + userId +
        ", 收货人=" + 收货人 +
        ", 收货人手机号=" + 收货人手机号 +
        ", 收货地址=" + 收货地址 +
        "}";
    }
}
