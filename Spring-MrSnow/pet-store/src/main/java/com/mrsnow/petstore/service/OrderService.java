package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.BuyInfo;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface OrderService extends IService<Order> {
    Order byFromDetail(JO<BuyInfo> jo) throws Exception;

    String pay(Order order);
}
