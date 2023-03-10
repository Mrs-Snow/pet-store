package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrsnow.petstore.dao.ShipAddress;
import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.mapper.ShipAddressMapper;
import com.mrsnow.petstore.mapper.StoreMapper;
import com.mrsnow.petstore.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.JO;
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
    private final ShipAddressMapper shipAddressMapper;

    @Override
    public Store getByUserId(Long userId) {
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getManagerId,userId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String editStore(JO<Store> jo) {
        String extra = jo.getExtra();
        Long userId = Long.parseLong(extra);
        Store store = getByUserId(userId);
        Store data = jo.getData();


        if(store!=null){
            LambdaQueryWrapper<ShipAddress> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ShipAddress::getStoreId,store.getId());
            ShipAddress shipAddress = shipAddressMapper.selectOne(wrapper);
            if(shipAddress==null && data.getIsOpening().equals("1")){
                return "开店必须补全发货信息！";
            }
            data.setId(store.getId());
            updateById(data);
            return "修改成功！";
        }else {
            data.setIsOpening("0");
            data.setManagerId(userId);
            save(data);
            return "申请店铺完成！";
        }
    }
}
