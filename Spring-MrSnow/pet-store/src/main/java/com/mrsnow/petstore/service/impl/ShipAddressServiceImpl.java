package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.ShipAddress;
import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.mapper.ShipAddressMapper;
import com.mrsnow.petstore.mapper.StoreMapper;
import com.mrsnow.petstore.service.ShipAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.ArgUtils;
import com.mrsnow.petstore.utils.JO;
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
    private final StoreMapper storeMapper;
    @Override
    public ShipAddress getInfo(JO<Long> jo) {
        LambdaQueryWrapper<ShipAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShipAddress::getStoreId,jo.getData());
        return getOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShipAddress updateInfo(JO<ShipAddress> jo) {
        ShipAddress shipAddress = jo.getData();

        LambdaQueryWrapper<ShipAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShipAddress::getStoreId,jo.getData());
        ShipAddress one = getOne(wrapper);

        if(one!=null){
            updateById(shipAddress);
        }else {
            String storeId = shipAddress.getStoreId();
            Store store = storeMapper.selectById(storeId);
            shipAddress.setStoreName(store.getStoreName());
            ArgUtils.ifNull(shipAddress.getId(),()->save(shipAddress));
        }

        return shipAddress;
    }
}
