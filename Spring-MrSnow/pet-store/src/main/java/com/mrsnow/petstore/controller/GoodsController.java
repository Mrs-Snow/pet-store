package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.service.GoodsService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
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


    @PostMapping(value = "/getStoreGoods")
    public R getStoreGoods(@RequestBody PJO<Goods> jo){
        Page<Goods> page = goodsService.getStore(jo);
        return R.success(page,"查询完成");

    }
    @PostMapping(value = "/searchGoods")
    public R searchByKey(@RequestBody PJO<String> jo){
        Page<Goods> goods = goodsService.searchGoods(jo);
        return R.success(goods,"搜索完成！");
    }

    @PostMapping(value = "/goodsDetail")
    public R goodsDetail(@RequestBody JO<Long> jo){
        Goods goods = goodsService.goodsDetail(jo);
        return R.success(goods,"查询完成！");
    }

    @PostMapping(value = "/getStore")
    public R getStore(@RequestBody PJO<Long> jo){
        Page<Goods> goods = goodsService.getStoreByStoreId(jo);
        return R.success(goods,"查询完成！");
    }

    @PostMapping(value = "/delete")
    public R delete(@RequestBody JO<List<Long>> jo){
        List<Long> data = jo.getData();
        goodsService.removeByIds(data);
        return R.success("删除完成");
    }

    @PostMapping(value = "/edit")
    public R edit(@RequestBody JO<Goods> jo){
        Goods goods = jo.getData();
        goodsService.edit(goods);
        return R.success("保存完成！");
    }

    @PostMapping(value = "/add")
    public R add(@RequestBody JO<Goods> jo)  {
        Goods goods = jo.getData();
        try {
            goodsService.addGoods(goods);
            return R.success("保存完成！");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }


}

