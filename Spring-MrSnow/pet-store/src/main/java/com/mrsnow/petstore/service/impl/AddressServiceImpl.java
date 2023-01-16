package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Address;
import com.mrsnow.petstore.mapper.AddressMapper;
import com.mrsnow.petstore.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收获地址表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

}
