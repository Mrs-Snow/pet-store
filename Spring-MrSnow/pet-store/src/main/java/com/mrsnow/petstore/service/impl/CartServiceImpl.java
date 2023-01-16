package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.mapper.CartMapper;
import com.mrsnow.petstore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
