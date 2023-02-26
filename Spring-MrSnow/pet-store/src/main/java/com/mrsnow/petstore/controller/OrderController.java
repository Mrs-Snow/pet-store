package com.mrsnow.petstore.controller;




import com.mrsnow.petstore.dao.BuyInfo;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Order;
import com.mrsnow.petstore.service.OrderService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private final OrderService orderService;

    @PostMapping(value = "/buyFromDetail")
    public R buyFromDetail(@RequestBody JO<BuyInfo> jo){
        try {
            Order order = orderService.byFromDetail(jo);
            return R.success(order,"创建订单成功!");
        } catch (Exception e) {
            return R.fail(e.getMessage());
        }
    }
}

