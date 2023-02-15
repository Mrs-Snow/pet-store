package com.mrsnow.petstore.service.impl;

import com.mrsnow.petstore.dao.Order;
import com.mrsnow.petstore.mapper.OrderMapper;
import com.mrsnow.petstore.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
