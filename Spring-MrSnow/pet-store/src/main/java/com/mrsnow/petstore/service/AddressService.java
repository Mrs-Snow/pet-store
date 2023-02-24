package com.mrsnow.petstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Address;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;

/**
 * <p>
 * 收获地址表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface AddressService extends IService<Address> {
    boolean addAddress(JO<Address> jo);
    IPage<Address> page(PJO<Long> jo);
}
