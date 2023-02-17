package com.mrsnow.petstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;

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
    IPage<Cart> getMyCart(PJO<Long> pjo);

    void addCart(JO<Cart> jo) throws Exception;
}
