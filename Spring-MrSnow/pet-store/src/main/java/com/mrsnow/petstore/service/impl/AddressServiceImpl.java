package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Address;
import com.mrsnow.petstore.mapper.AddressMapper;
import com.mrsnow.petstore.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 收获地址表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addAddress(JO<Address> jo) {
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Address::getUserId,jo.getData().getUserId());
        int count = count(wrapper);
        if(count<=10){
            save(jo.getData());
            return true;
        }
        return false;
    }

    @Override
    public IPage<Address> page(PJO<Long> jo) {
        Page<Address> page = new Page<>(jo.getCurrent(), 5);
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Address::getUserId,jo.getData());
        return page(page,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setDefault(JO<Long> jo) {
        //把当前默认的设置成不默认
        LambdaUpdateWrapper<Address> wrapper1 = new LambdaUpdateWrapper<>();
        wrapper1.eq(Address::getIsDefault,"1").set(Address::getIsDefault,"0");
        update(wrapper1);
        //设置当前默认
        LambdaUpdateWrapper<Address> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Address::getIsDefault,"1").eq(Address::getId,jo.getData());
        update(wrapper);
    }
}
