package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.service.CartService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Controller
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping(value = "/myCart")
    public R<List<Cart>> myCart(@RequestBody JO<Long> jo){
        Long userId = jo.getData();
        List<Cart> carts = cartService.getMyCart(userId);
        return R.success(carts);
    }

}

