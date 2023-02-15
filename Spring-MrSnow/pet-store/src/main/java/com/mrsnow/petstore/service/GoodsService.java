package com.mrsnow.petstore.service;

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
    Page<Goods> searchGoods(PJO<String> jo);

    Goods goodsDetail(JO<Long> jo);

}
