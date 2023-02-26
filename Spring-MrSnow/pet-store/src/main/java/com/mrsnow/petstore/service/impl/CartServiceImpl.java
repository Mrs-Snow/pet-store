package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Cart;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.mapper.CartMapper;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    private final GoodsMapper goodsMapper;

    /**
     * 01-未结算 02-已结算
     * @param pjo
     * @return
     */
    @Override
    public IPage<Cart> getMyCart(@RequestBody PJO<Long> pjo) {
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Cart> queryWrapper = wrapper.eq(Cart::getUserId, pjo.getData())
                .eq(Cart::getIsAccount,"01");
        Page<Cart> page = new Page<>(pjo.getCurrent(), 4);
        Page<Cart> cartPage = baseMapper.selectPage(page, queryWrapper);
        for (Cart record : cartPage.getRecords()) {
            Goods goods = goodsMapper.selectById(record.getGoodsId());
            record.setGoodsDetail(goods);
        }
        return cartPage;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCart(JO<Cart> jo) throws Exception {
        Cart cart = jo.getData();
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        //查该用户购物车中是否有同样的商品(未结算的）
        wrapper.eq(Cart::getGoodsId,cart.getGoodsId()).eq(Cart::getUserId,cart.getUserId());
        List<Cart> carts = baseMapper.selectList(wrapper);
        //有则更新数值金额，无则新增一条记录
        if (!carts.isEmpty()){
            Cart cart1 = carts.get(0);
            //数量累加
            int num = cart1.getGoodsNum() + cart.getGoodsNum();
            //查商品库存是否够
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            if(num>goods.getInventoryNum()){
                throw new Exception("本次添加后购物车中该商品的数量超出库存上限！");
            }
            cart1.setGoodsNum(num);
            //金额累加
            BigDecimal money = cart1.getAmountMoney();
            BigDecimal money1 = cart.getAmountMoney();
            cart1.setAmountMoney(money.add(money1));
            updateById(cart1);
        }else {
            save(jo.getData());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void numEdit(JO<Cart> jo) {
        Cart data = jo.getData();
        Long goodsId = data.getGoodsId();
        Goods goods = goodsMapper.selectById(goodsId);
        BigDecimal price = goods.getPrice();
        data.setAmountMoney(price.multiply(new BigDecimal(data.getGoodsNum())));
        updateById(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeAll(JO<Long> jo) {
        Long userId = jo.getData();
        LambdaQueryWrapper<Cart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Cart::getUserId,userId);
        remove(wrapper);
    }
}
