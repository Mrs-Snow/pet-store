package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.ShipAddress;
import com.mrsnow.petstore.service.ShipAddressService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 发货地址表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/shipAddress")
@CrossOrigin
@RequiredArgsConstructor
public class ShipAddressController {
    private final ShipAddressService shipAddressService;

    @PostMapping(value = "/getInfo")
    public R getInfo(@RequestBody JO<Long> jo){
        ShipAddress info = shipAddressService.getInfo(jo);
        return R.success(info,"查询完成");
    }

    @PostMapping(value = "/updateInfo")
    public R updateInfo(@RequestBody JO<ShipAddress> jo){
        shipAddressService.updateInfo(jo);
        return R.success("更新完成");
    }
}

