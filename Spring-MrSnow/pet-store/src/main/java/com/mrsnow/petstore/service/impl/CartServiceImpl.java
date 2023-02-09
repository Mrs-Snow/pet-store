package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.mapper.CartMapper;
import com.mrsnow.petstore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Cart> getMyCart(Long userId) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Cart> queryWrapper = wrapper.eq(Cart::getUserId, userId);
        return baseMapper.selectList(queryWrapper);
    }
}
