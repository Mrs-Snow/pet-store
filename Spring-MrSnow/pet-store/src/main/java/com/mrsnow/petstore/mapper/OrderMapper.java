package com.mrsnow.petstore.mapper;

import com.mrsnow.petstore.dao.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface OrderMapper extends BaseMapper<Order> {

}
