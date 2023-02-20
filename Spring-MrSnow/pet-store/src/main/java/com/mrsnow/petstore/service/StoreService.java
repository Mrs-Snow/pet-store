package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.Store;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商店表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface StoreService extends IService<Store> {
    Store getByUserId(Long userId);
}
