package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        Page<Goods> goods = goodsService.searchGoodByKey(jo.getData());
        return R.success(goods,"搜索完成！");
    }

    @PostMapping(value = "/searchByKind")
    public R searchByKind(@RequestBody JO<String> jo){
        Page<Goods> goods = goodsService.searchGoodByKind(jo.getData());
        return R.success(goods,"搜索完成！");
    }
}

