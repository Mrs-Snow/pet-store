package com.mrsnow.petstore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    Page<Goods> searchGoodByKey(String searchKey,int current,int pageSize);
    Page<Goods> searchGoodByKind(String kind,int current,int pageSize);
}
