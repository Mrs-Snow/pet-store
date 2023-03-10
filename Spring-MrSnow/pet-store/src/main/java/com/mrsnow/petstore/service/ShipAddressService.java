package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.ShipAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;

/**
 * <p>
 * 发货地址表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface ShipAddressService extends IService<ShipAddress> {
    ShipAddress getInfo(JO<Long> jo);
    ShipAddress updateInfo(JO<ShipAddress> jo);
}
