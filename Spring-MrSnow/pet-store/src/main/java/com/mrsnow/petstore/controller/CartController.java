package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.service.CartService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import com.mrsnow.petstore.utils.R;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping(value = "/myCart")
    public R<IPage<Cart>> myCart(@RequestBody PJO<Long> jo){
        IPage<Cart> carts = cartService.getMyCart(jo);
        return R.success(carts,"查询完成");
    }

    @PostMapping(value = "/edit")
    public R edit(@RequestBody JO<Cart> jo){
        cartService.numEdit(jo);
        return R.success("更新完成");
    }

    @PostMapping(value = "/remove")
    public R remove(@RequestBody JO<Long> jo){
        cartService.removeById(jo.getData());
        return R.success("删除完成");
    }

    @PostMapping(value = "/removeByIds")
    public R removeByIds(@RequestBody JO<List<Long>> jo){
        cartService.removeByIds(jo.getData());
        return R.success("删除完成");
    }

    @PostMapping(value = "/removeAll")
    public R removeAll(@RequestBody JO<Long> jo){
        cartService.removeAll(jo);
        return R.success("删除完成");
    }

    @PostMapping(value = "/addCart")
    public R addCart(@RequestBody JO<Cart> jo){
        try {
            cartService.addCart(jo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.success("添加完成");
    }



}

