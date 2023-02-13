package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.PJO;
import org.apache.commons.lang3.StringUtils;
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

}
