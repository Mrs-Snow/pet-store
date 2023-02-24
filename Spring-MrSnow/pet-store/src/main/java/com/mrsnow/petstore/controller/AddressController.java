package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Address;
import com.mrsnow.petstore.service.AddressService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import com.mrsnow.petstore.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 收获地址表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@CrossOrigin
public class AddressController {
    private final AddressService addressService;

    @PostMapping(value ="/add")
    public R add(@RequestBody JO<Address> jo){
        boolean address = addressService.addAddress(jo);
        if(address){
            return R.success("保存完成");
        }else {
            return R.fail("一个用户最多保存十条地址！");
        }
    }

    @PostMapping(value = "/list")
    public R page(@RequestBody PJO<Long> pjo){
        IPage<Address> page = addressService.page(pjo);
        return R.success(page,"查询完成");
    }

    @PostMapping(value = "/edit")
    public R edit(@RequestBody JO<Address> jo){
        addressService.updateById(jo.getData());
        return R.success("查询完成");
    }

    @PostMapping(value = "/remove")
    public R remove(@RequestBody JO<List<Long>> jo){
        addressService.removeByIds(jo.getData());
        return R.success("删除完成");
    }
}

