package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.mapper.StoreMapper;
import com.mrsnow.petstore.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商店表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

}
