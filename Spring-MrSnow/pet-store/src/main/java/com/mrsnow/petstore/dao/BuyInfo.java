package com.mrsnow.petstore.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-26  16:37
 **/
@Data
@ToString
public class BuyInfo {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    private Integer num;
}
