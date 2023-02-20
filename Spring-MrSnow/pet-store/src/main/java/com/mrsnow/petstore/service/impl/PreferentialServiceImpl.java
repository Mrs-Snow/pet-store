package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Preferential;
import com.mrsnow.petstore.mapper.PreferentialMapper;
import com.mrsnow.petstore.service.PreferentialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.PJO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 优惠表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreferentialServiceImpl extends ServiceImpl<PreferentialMapper, Preferential> implements PreferentialService {

    @Override
    public IPage<Preferential> pageQuery(PJO<Long> pjo) {
        Page<Preferential> page = new Page<>(pjo.getCurrent(), pjo.getPageSize());
        LambdaQueryWrapper<Preferential> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Preferential::getStoreId,pjo.getData());
        return baseMapper.selectPage(page,wrapper);
    }
}
