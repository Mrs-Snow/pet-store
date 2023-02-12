package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Page<Goods> searchGood(String searchKey) {
        Page<Goods> goodsPage = new Page<>(1,6);
        LambdaQueryChainWrapper<Goods> wrapper = new LambdaQueryChainWrapper<>(baseMapper);
        Page<Goods> page = wrapper.like(Goods::getClassName, searchKey)
                .page(goodsPage);
        return page;
    }
}
