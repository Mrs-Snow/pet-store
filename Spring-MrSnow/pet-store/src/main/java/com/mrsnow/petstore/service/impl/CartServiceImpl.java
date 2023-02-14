package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.mapper.CartMapper;
import com.mrsnow.petstore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.PJO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    public IPage<Cart> getMyCart(@RequestBody PJO<Long> pjo) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Cart> queryWrapper = wrapper.eq(Cart::getUserId, pjo.getData());
        Page<Cart> page = new Page<>(pjo.getCurrent(), pjo.getPageSize());
        return baseMapper.selectPage(page,queryWrapper);
    }
}
