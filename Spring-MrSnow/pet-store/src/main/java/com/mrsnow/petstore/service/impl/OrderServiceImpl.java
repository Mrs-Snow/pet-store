package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrsnow.petstore.dao.*;
import com.mrsnow.petstore.mapper.*;
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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

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
    private final CartMapper cartMapper;
    private final AddressMapper addressMapper;
    private final PreferentialMapper preferentialMapper;
    private final StoreMapper storeMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeOrder(Order order) throws Exception {
        String status = order.getStatus();
        if(!status.equals("已完成")&&!status.equals("已退款")){
            throw new Exception("订单尚未完成！");
        }

        removeById(order.getId());
    }

    @Override
    public synchronized List<Order> buyFromCart(JO<List<Long>> jo) throws Exception {
        List<Long> cartIds = jo.getData();


        ArrayList<Order> orders = new ArrayList<>();
        for (Long id:cartIds){
            Cart cart = cartMapper.selectById(id);
            Long goodsId = cart.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            Long preferentialId = goods.getPreferentialId();
            if(preferentialId!=null){
                Preferential preferential = preferentialMapper.selectById(preferentialId);
                ArgUtils.ifNotNull(preferential,()->goods.setPreferential(preferential));
            }

            int num = cart.getGoodsNum();
            goods.setNum(num);
            //校验
            Long storeId = goods.getStoreId();
            Store store = storeMapper.selectById(storeId);
            if(store.getIsOpening().equals("0")){
                throw new Exception(store.getStoreName()+"暂未营业！");
            }
            if(goods.getInventoryNum()<1){
                throw new Exception(goods.getGoodsName()+"的库存已告罄！");
            }
            if(goods.getInventoryNum()<num){
                throw new Exception(goods.getGoodsName()+"的库存不足！");
            }

            Order order = setOrder(goods, num, cart.getUserId());
            order.setCartId(id);
            orders.add(order);
        }

        return orders;
    }

    @Override
    public synchronized Order byFromDetail(JO<BuyInfo> jo) throws Exception {
        //参数处理
        BuyInfo buyInfo = jo.getData();
        //查询商品详情
        Goods goods = goodsMapper.selectById(buyInfo.getGoodsId());
        Long userId = buyInfo.getUserId();
        int num = buyInfo.getNum();
        goods.setNum(num);
        //拿优惠ID
        Long preferentialId = goods.getPreferentialId();

        if (preferentialId!=null){
            Preferential preferential = preferentialMapper.selectById(preferentialId);
            //如果优惠不为空，则设置优惠信息
            ArgUtils.ifNotNull(preferential,()->goods.setPreferential(preferential));
        }


        //校验
        Long storeId = goods.getStoreId();
        Store store = storeMapper.selectById(storeId);
        if(store.getIsOpening().equals("0")){
            throw new Exception(store.getStoreName()+"暂未营业！");
        }
        if(goods.getInventoryNum()<1){
            throw new Exception("库存已告罄！");
        }
        if(goods.getInventoryNum()<num){
            throw new Exception("库存不足！");
        }
        return setOrder(goods, num, userId);
    }

    /**
     * 设置订单
     * @param goods
     * @param num
     * @param userId
     * @return
     */
    private Order setOrder(Goods goods,int num,Long userId){
        //生成订单
        Order order = new Order();
        order.setGoodsDetail(goods);
        order.setGoodsNum(num);
        order.setGoodsName(goods.getGoodsName());
        order.setOrderNo(getOrderNo());
        order = setAmount(order, goods, num);
        order.setOrderTime(LocalDate.now());
        order.setGoodsId(goods.getId());
        //状态码：01未支付 02待发货 03已发货 04已签收 05退款中 06已完成
        order.setStatus("未支付");
        order.setUserId(userId);
        order.setStoreId(goods.getStoreId());

        //收获信息
        LambdaQueryWrapper<Address> addressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressLambdaQueryWrapper.eq(Address::getUserId,userId).eq(Address::getIsDefault,"1");
        Address address = addressMapper.selectOne(addressLambdaQueryWrapper);
        if(address!=null){
            order.setAddress(address);
            order.setAddressId(address.getId());
        }



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
        //设置
        order.setStatus("待发货");

        //购物车清除记录
        Long cartId = order.getCartId();
        ArgUtils.ifNotNull(cartId,()->{
            cartMapper.deleteById(cartId);
        });
        save(order);
        return "支付完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String send(Order order) {
        //设置
        order.setStatus("待签收");

        //修改库存量
        Long goodsId = order.getGoodsId();
        Goods goods = goodsMapper.selectById(goodsId);
        goods.setInventoryNum(goods.getInventoryNum()-order.getGoodsNum());

        goodsMapper.updateById(goods);
        updateById(order);

        return "已发货";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String receive(Order order) {
        //设置
        order.setStatus("已签收");
        updateById(order);
        return "签收完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String confirm(Order order) {
        //设置
        order.setStatus("已完成");

        //修改库存量
//        Long goodsId = order.getGoodsId();
//        Goods goods = goodsMapper.selectById(goodsId);
//        goods.setInventoryNum(goods.getInventoryNum()-order.getGoodsNum());

//        goodsMapper.updateById(goods);
        updateById(order);
        return "修改完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String refuse(Order order) {
        //设置
        if(order.getExpressNo()!=null){
            order.setStatus("待签收");
        }else{
            order.setStatus("待发货");
        }
        //修改库存量
//        Long goodsId = order.getGoodsId();
//        Goods goods = goodsMapper.selectById(goodsId);
//        goods.setInventoryNum(goods.getInventoryNum()+order.getGoodsNum());
//
//        goodsMapper.updateById(goods);
        updateById(order);
        return "修改完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancel(Order order) {
        //设置
        order.setStatus("已退款");

        //退库存
        Long goodsId = order.getGoodsId();
        Goods update = goodsMapper.selectById(goodsId);
        update.setInventoryNum(update.getInventoryNum()+order.getGoodsNum());
        goodsMapper.updateById(update);
        updateById(order);
        return "退款完成";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancelApply(Order order) {
        //设置
        order.setStatus("退款中");
        updateById(order);
        return "已申请";
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
            BigDecimal discountValue = ArgUtils.getValue(preferential.getDiscount(),new BigDecimal("100"));
            BigDecimal preferentialPrice = ArgUtils.getValue(preferential.getPreferentialPrice(),new BigDecimal("0.00"));
            //不优惠 无论是否满足优惠条件，都按原价计算
            if(discountValue.compareTo(new BigDecimal("100"))==0 && preferentialPrice.equals(new BigDecimal("0.00"))){
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
                if(!preferential.getPreferentialPrice().equals(new BigDecimal("0.00"))){
                    //before - preferentialPrice
                    before = before.subtract(preferential.getPreferentialPrice());
                    remark=remark+"立减"+preferential.getPreferentialPrice().toString()+"元，";
                }
                //打折不为100，有打折
                if(!preferential.getDiscount().equals(new BigDecimal(100))){
                    //折扣
                    BigDecimal discount = getDiscount(preferential.getDiscount().intValue());
                    //折后价格
                    before=before.multiply(discount);
                    remark=remark+discountValue+"折，";
                }
                //计算完成，设置价格
                order.setAmount(before);
                remark=remark+"已为您优惠："+(price.multiply(goodsNum).subtract(before)).setScale(2, RoundingMode.HALF_UP)+"元";
                order.setRemark(remark);
            }else {
                //未达条件,或没优惠
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

    private BigDecimal getDiscount(int countValue){
        //折扣率
        BigDecimal ratio = new BigDecimal("0.01");

        //1-9的数字
        if(countValue>0&&countValue<10){
            countValue*=10;
        }
        BigDecimal value=new BigDecimal(countValue);
        return ratio.multiply(value);
    }
}
