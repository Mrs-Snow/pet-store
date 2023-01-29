package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

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
    List<Goods> searchGood(String searchKey);
}
