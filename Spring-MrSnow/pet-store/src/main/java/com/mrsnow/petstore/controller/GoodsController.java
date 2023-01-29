package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.service.GoodsService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
    public R searchByKey(JO<String> key){
        return R.success("将大赛大家撒");
    }
}

