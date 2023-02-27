package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.*;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.mapper.OrderMapper;
import com.mrsnow.petstore.mapper.ShipAddressMapper;
import com.mrsnow.petstore.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.utils.ArgUtils;
import com.mrsnow.petstore.utils.JO;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

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
    private final GoodsMapper goodsMapper;
    private final ShipAddressMapper shipAddressMapper;

    @Override
    public Order byFromDetail(JO<BuyInfo> jo) throws Exception {
        //参数处理
        BuyInfo buyInfo = jo.getData();
        Goods goods = goodsMapper.selectById(buyInfo.getGoodsId());
        Long userId = buyInfo.getUserId();
        int num = buyInfo.getNum();
        goods.setNum(num);

        //校验
        if(goods.getInventoryNum()<1){
            throw new Exception("库存已告罄！");
        }
        if(goods.getInventoryNum()<num){
            throw new Exception("库存不足！");
        }

        //生成订单
        Order order = new Order();
        order.setGoodsDetail(goods);
        order.setOrderNo(getOrderNo());
        order = setAmount(order, goods, num);
        order.setOrderTime(LocalDate.now());
        order.setGoodsId(goods.getId());
        //状态码：01未支付 02待发货 03已发货 04已签收 05退款中 06已完成
        order.setStatus("01");
        order.setUserId(userId);
        order.setStoreId(goods.getStoreId());

        //发货信息
        LambdaQueryWrapper<ShipAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShipAddress::getStoreId,goods.getStoreId());
        ShipAddress shipAddress = shipAddressMapper.selectOne(wrapper);
        order.setShipAddress(shipAddress);

        return order;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String pay(Order order) {
        //设置待发货
        order.setStatus("02");
        save(order);
        return "支付完成";
    }

    /**
     * uuid简易实现生成一个不重复的订单号
     * @return
     */
    private String getOrderNo(){
        UUID uuid = UUID.randomUUID();
        return String.valueOf(uuid).toUpperCase().replace("-", "");
    }

    /**
     * 根据优惠情况设置总金额
     * @param order
     */
    private Order setAmount(Order order,Goods goods,int num){
        //计算订单金额
        BigDecimal goodsNum = new BigDecimal(num);
        BigDecimal price = goods.getPrice();
        BigDecimal amount = null;
        Preferential preferential = goods.getPreferential();
        //设置了优惠
        if(preferential!=null){

            //先拿到达到优惠条件的阈值
            int countValue = ArgUtils.getValue(preferential.getCountValue(),1);
            BigDecimal priceValue = ArgUtils.getValue(preferential.getPriceValue(),new BigDecimal(0));
            //不优惠 无论是否满足优惠条件，都按原价计算
            if(countValue==0 && priceValue.equals(new BigDecimal(0))){
                amount=goodsNum.multiply(price);
                order.setRemark("无实际优惠活动或未达到优惠条件");
                order.setAmount(amount);
                return order;
            }

            //满足数量条件 或者满足金额条件 先立减，再打折
            if(num>=countValue || price.multiply(goodsNum).compareTo(preferential.getPriceValue())>=0 ){
                String remark ="已满足优惠条件，此单";
                //优惠前价格
                BigDecimal before = price.multiply(goodsNum);

                //立减不为0 有立减
                if(!preferential.getPreferentialPrice().equals(new BigDecimal(0))){
                    before = before.subtract(preferential.getPreferentialPrice());
                    remark=remark+"立减"+preferential.getPreferentialPrice().toString()+"元，";
                }
                //打折不为100，有打折
                if(!preferential.getDiscount().equals(new BigDecimal(100))){

                    //折扣系数
                    BigDecimal ratio = new BigDecimal("0.01");
                    BigDecimal value = new BigDecimal(countValue);
                    BigDecimal discount = ratio.multiply(value);
                    //折后价格
                    before=before.multiply(discount);
                    remark=remark+countValue+"折，";
                }
                order.setAmount(before);
                remark=remark+"已为您优惠："+price.multiply(goodsNum).subtract(before)+"元";
                order.setRemark(remark);
            }else {
                //未达条件,没优惠
                order.setAmount(goodsNum.multiply(price));
                order.setRemark("此单未满足优惠条件");
            }
            return order;
        }else {
            //没优惠
            order.setAmount(goodsNum.multiply(price));
            order.setRemark("无优惠活动");
            return order;
        }
    }
}
