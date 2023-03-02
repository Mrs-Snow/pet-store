package com.mrsnow.petstore.service;

import com.mrsnow.petstore.dao.BuyInfo;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mrsnow.petstore.utils.JO;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
public interface OrderService extends IService<Order> {

    void removeOrder(Order order) throws Exception;

    List<Order> buyFromCart(JO<List<BuyInfo>> jo) throws Exception;

    Order byFromDetail(JO<BuyInfo> jo) throws Exception;

    String pay(Order order);
    String send(Order order);
    String receive(Order order);
    String cancel(Order order);
    String cancelApply(Order order);
    String confirm(Order order);
}
