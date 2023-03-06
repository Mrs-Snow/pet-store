package com.mrsnow.petstore.controller;




import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Address;
import com.mrsnow.petstore.dao.BuyInfo;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Order;
import com.mrsnow.petstore.service.AddressService;
import com.mrsnow.petstore.service.GoodsService;
import com.mrsnow.petstore.service.OrderService;
import com.mrsnow.petstore.utils.ArgUtils;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import com.mrsnow.petstore.utils.R;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {
    private final AddressService addressService;
    private final OrderService orderService;
    private final GoodsService goodsService;

    @PostMapping(value = "/buyFromDetail")
    public R buyFromDetail(@RequestBody JO<BuyInfo> jo){
        try {
            Order order = orderService.byFromDetail(jo);
            return R.success(order,"创建订单成功!");
        } catch (Exception e) {
            return R.info(e.getMessage());
        }
    }

    @PostMapping(value = "/buyFromCart")
    public R buyFromCart(@RequestBody JO<List<Long>> jo){
        try {
            List<Order> orders = orderService.buyFromCart(jo);
            return R.success(orders,"创建订单成功!");
        } catch (Exception e) {
            return R.info(e.getMessage());
        }
    }

    @PostMapping(value = "/pay")
    public R pay(@RequestBody JO<Order> jo){
        String str = orderService.pay(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/send")
    public R send(@RequestBody JO<Order> jo){
        String str = orderService.send(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/cancelApply")
    public R cancelApply(@RequestBody JO<Order> jo){
        String str = orderService.cancelApply(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/receive")
    public R receive(@RequestBody JO<Order> jo){
        String str = orderService.receive(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/confirm")
    public R confirm(@RequestBody JO<Order> jo){
        String str = orderService.confirm(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/refuse")
    public R refuse(@RequestBody JO<Order> jo){
        String str = orderService.refuse(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/cancel")
    public R cancel(@RequestBody JO<Order> jo){
        String str = orderService.cancel(jo.getData());
        return R.success(str);
    }

    @PostMapping(value = "/remove")
    public R removeOrder(@RequestBody JO<Order> jo){
        try {
            orderService.removeOrder(jo.getData());
            return R.success("删除完成");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }

    @PostMapping(value = "/getById")
    public R getById(@RequestBody JO<Long> jo){
        Order order = orderService.getById(jo.getData());
        Long addressId = order.getAddressId();
        Address address = addressService.getById(addressId);
        order.setAddress(address);
        Goods goods = goodsService.getById(order.getGoodsId());
        order.setGoodsDetail(goods);
        return R.success(order,"查询完成");
    }

    @PostMapping(value = "/userList")
    public R listByUserId(@RequestBody PJO<Order> jo){
        Order order = jo.getData();
        Page<Order> page = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId,order.getUserId());



        ArgUtils.ifNotNull(order.getOrderNo(),()->wrapper.like(Order::getOrderNo,order.getOrderNo()));
        ArgUtils.ifNotNull(order.getStatus(),()->wrapper.like(Order::getStatus,order.getStatus()));
        ArgUtils.ifNotNull(order.getGoodsName(),()->wrapper.like(Order::getGoodsName,order.getGoodsName()));

        IPage<Order> orders = orderService.page(page,wrapper);

        for (Order record : orders.getRecords()) {
            Long goodsId = record.getGoodsId();
            Goods goods = goodsService.getById(goodsId);
            record.setGoodsDetail(goods);
        }

        return R.success(orders,"查询完成");
    }

    @PostMapping(value = "/storeList")
    public R listByStoreId(@RequestBody PJO<Order> jo){
        Order order = jo.getData();
        Page<Order> page = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getStoreId,order.getStoreId());

        ArgUtils.ifNotNull(order.getOrderNo(),()->wrapper.like(Order::getOrderNo,order.getOrderNo()));
        ArgUtils.ifNotNull(order.getStatus(),()->wrapper.like(Order::getStatus,order.getStatus()));
        ArgUtils.ifNotNull(order.getGoodsName(),()->wrapper.like(Order::getGoodsName,order.getGoodsName()));

        IPage<Order> orders = orderService.page(page,wrapper);

        for (Order record : orders.getRecords()) {
            Long goodsId = record.getGoodsId();
            Goods goods = goodsService.getById(goodsId);
            record.setGoodsDetail(goods);
        }
        return R.success(orders,"查询完成");
    }
}

