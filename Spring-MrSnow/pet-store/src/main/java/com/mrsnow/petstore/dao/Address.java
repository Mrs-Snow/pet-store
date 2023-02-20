package com.mrsnow.petstore.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Data
@ToString(callSuper = true)
public class Address extends Model<Address> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "USER_ID",condition = SqlCondition.LIKE)
    private Long userId;

    /**
     * 收货人
     */
    @TableField(value = "CONSIGNEE",condition = SqlCondition.LIKE)
    private String consignee;

    /**
     * 收货人手机号
     */
    @TableField(value = "CONSIGNEE_TEL",condition = SqlCondition.LIKE)
    private String consigneeTel;

    /**
     * 收货地址
     */
    @TableField(value = "DELIVERY_ADDRESS",condition = SqlCondition.LIKE)
    private String deliveryAddress;

}
