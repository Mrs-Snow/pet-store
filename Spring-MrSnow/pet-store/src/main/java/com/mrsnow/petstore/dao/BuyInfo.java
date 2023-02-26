package com.mrsnow.petstore.dao;

import lombok.Data;
import lombok.ToString;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-26  16:37
 **/
@Data
@ToString
public class BuyInfo {
    private Long goodsId;
    private Long userId;
    private Integer num;
}
