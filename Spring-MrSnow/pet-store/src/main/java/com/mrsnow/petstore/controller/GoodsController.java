package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.service.GoodsService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping(value = "/searchByKey")
    public R searchByKey(@RequestBody JO<String> jo){
        List<Goods> goods = goodsService.searchGood(jo.getData());
        return R.success(goods,"搜索完成！");
    }
}

