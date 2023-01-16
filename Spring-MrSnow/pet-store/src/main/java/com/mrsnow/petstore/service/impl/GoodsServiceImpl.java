package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
