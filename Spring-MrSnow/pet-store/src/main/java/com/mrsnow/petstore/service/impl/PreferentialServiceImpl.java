package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Preferential;
import com.mrsnow.petstore.mapper.PreferentialMapper;
import com.mrsnow.petstore.service.PreferentialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
