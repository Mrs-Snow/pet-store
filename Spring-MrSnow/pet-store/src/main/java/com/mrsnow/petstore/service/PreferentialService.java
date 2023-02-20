package com.mrsnow.petstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Preferential;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.PJO;

/**
 * <p>
 * 优惠表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface PreferentialService extends IService<Preferential> {
    IPage<Preferential> pageQuery(PJO<Long> pjo);
}
