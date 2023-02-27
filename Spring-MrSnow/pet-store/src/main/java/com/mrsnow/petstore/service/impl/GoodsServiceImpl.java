package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Preferential;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.mapper.PreferentialMapper;
import com.mrsnow.petstore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.service.PreferentialService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final PreferentialMapper PreferentialMapper;
    @Override
    public Page<Goods> searchGoods(PJO<String> jo) {
        Page<Goods> goodsPage = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        //关键词过滤
        wrapper.like(Goods::getGoodsName, jo.getData());
        //如果有分类参数
        if(StringUtils.isNotEmpty(jo.getExtra())){
            wrapper.like(Goods::getClassName,jo.getExtra());
        }
        //分页查询
        return baseMapper.selectPage(goodsPage,wrapper);

    }

    @Override
    public Page<Goods> getStore(PJO<Long> jo) {
        Page<Goods> goodsPage = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getStoreId,jo.getData());
        return baseMapper.selectPage(goodsPage,wrapper);
    }

    @Override
    public Goods goodsDetail(JO<Long> jo) {
        Goods goods = baseMapper.selectById(jo.getData());
        //查优惠活动
        if (goods.getPreferentialId()!=null){
            Preferential preferential = PreferentialMapper.selectById(goods.getPreferentialId());
            goods.setPreferential(preferential);
        }
        return goods;
    }

}
