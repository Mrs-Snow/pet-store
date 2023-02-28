package com.mrsnow.petstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface GoodsService extends IService<Goods> {
    Goods edit(Goods goods);
    Page<Goods> searchGoods(PJO<String> jo);

    Page<Goods> getStore(PJO<Goods> jo);

    Page<Goods> getStoreByStoreId(PJO<Long> jo);

    Goods goodsDetail(JO<Long> jo);

    Goods addGoods(Goods goods) throws Exception;
//    Page<Goods> searchGoods(Long id,int current,int pageSize);




}
