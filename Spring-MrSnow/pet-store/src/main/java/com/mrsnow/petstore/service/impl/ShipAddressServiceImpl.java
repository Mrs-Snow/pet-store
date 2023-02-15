package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.ShipAddress;
import com.mrsnow.petstore.mapper.ShipAddressMapper;
import com.mrsnow.petstore.service.ShipAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 发货地址表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipAddressServiceImpl extends ServiceImpl<ShipAddressMapper, ShipAddress> implements ShipAddressService {

}
