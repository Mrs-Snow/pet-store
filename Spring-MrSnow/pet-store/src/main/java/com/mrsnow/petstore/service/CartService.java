package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface CartService extends IService<Cart> {
    List<Cart> getMyCart(Long userId);
}
