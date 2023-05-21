package com.mrsnow.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrsnow.petstore.dao.Goods;
import com.mrsnow.petstore.dao.Preferential;
import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.mapper.GoodsMapper;
import com.mrsnow.petstore.mapper.PreferentialMapper;
import com.mrsnow.petstore.mapper.StoreMapper;
import com.mrsnow.petstore.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrsnow.petstore.service.PreferentialService;
import com.mrsnow.petstore.utils.ArgUtils;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.PJO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    private final static String[] GOODS_IMG_FOOD = {"food2.jpg", "food3.jpg", "food4.jpg", "food5.jpg", "food6.jpg", "food7.jpg"};
    private final static String[] GOODS_IMG_MAOTIAO = {"maotiao1.webp", "maotiao2.webp"};
    private final static String[] GOODS_IMG_FANPEN = {"fanpen1.webp", "fanpen2.webp"};
    private final static String[] GOODS_IMG_BOHE = {"bohe1.webp", "bohe2.webp"};
    private final static String[] GOODS_IMG_DG = {"donggan1.webp", "donggan2.webp"};
    private final static String[] GOODS_IMG_GT = {"guantou1.webp", "guantou2.webp"};
    private final static String[] GOODS_IMG_MOYA = {"moya1.webp", "maocao1.webp", "maocao2.webp"};
    private final StoreMapper storeMapper;
    private final PreferentialMapper PreferentialMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods edit(Goods goods) {
        updateById(goods);
        return goods;
    }

    @Override
    public Page<Goods> searchGoods(PJO<String> jo) {
        Page<Goods> goodsPage = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        //关键词过滤
        wrapper.like(Goods::getGoodsName, jo.getData());
        //如果有分类参数
        if (StringUtils.isNotEmpty(jo.getExtra())) {
            wrapper.like(Goods::getClassName, jo.getExtra());
        }
        //分页查询
        return baseMapper.selectPage(goodsPage, wrapper);

    }

    @Override
    public Page<Goods> getStore(PJO<Goods> jo) {
        Goods goods = jo.getData();
        Page<Goods> goodsPage = new Page<>(jo.getCurrent(), 4);
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Goods::getStoreId, goods.getStoreId());
        ArgUtils.ifNotNull(goods.getGoodsName(), () -> wrapper.like(Goods::getGoodsName, goods.getGoodsName()));
        ArgUtils.ifNotNull(goods.getClassName(), () -> wrapper.like(Goods::getClassName, goods.getClassName()));
        return baseMapper.selectPage(goodsPage, wrapper);
    }

    @Override
    public Page<Goods> getStoreByStoreId(PJO<Long> jo) {
        Page<Goods> goodsPage = new Page<>(jo.getCurrent(), jo.getPageSize());
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getStoreId, jo.getData());
        return baseMapper.selectPage(goodsPage, wrapper);
    }

    @Override
    public Goods goodsDetail(JO<Long> jo) {
        Goods goods = baseMapper.selectById(jo.getData());
        //查优惠活动
        if (goods.getPreferentialId() != null) {
            Preferential preferential = PreferentialMapper.selectById(goods.getPreferentialId());
            goods.setPreferential(preferential);
        }
        return goods;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Goods addGoods(Goods goods) throws Exception {
        ArgUtils.ifNull(goods.getClassName(), "请选择品类！");
        ArgUtils.ifNull(goods.getGoodsName(), "商品名不能为空！");
        ArgUtils.ifNull(goods.getInventoryNum(), "请填写库存数量！");
        ArgUtils.ifNull(goods.getPrice(), "请设置单价！");
        Goods goods1 = setImg(goods);
        Store store = storeMapper.selectById(goods.getStoreId());
        goods1.setStoreName(store.getStoreName());
        save(goods1);
        return goods1;
    }

    private Goods setImg(Goods goods) {
        String s = goods.getClassName();
        switch (s) {
            case "罐头":
                goods.setGoodsPic(randomImg(GOODS_IMG_GT));
                break;
            case "猫条":
                goods.setGoodsPic(randomImg(GOODS_IMG_MAOTIAO));
                break;
            case "猫粮":
                goods.setGoodsPic(randomImg(GOODS_IMG_FOOD));
                break;
            case "冻干":
                goods.setGoodsPic(randomImg(GOODS_IMG_DG));
                break;
            case "磨牙食品":
                goods.setGoodsPic(randomImg(GOODS_IMG_MOYA));
                break;
            case "薄荷":
                goods.setGoodsPic(randomImg(GOODS_IMG_BOHE));
                break;
            case "饭盆":
                goods.setGoodsPic(randomImg(GOODS_IMG_FANPEN));
                break;
            case "饮料":
                goods.setGoodsPic(randomImg(GOODS_IMG_FANPEN));
                break;
            default:
                goods.setGoodsPic("food2.jpg");
        }
        return goods;
    }

    private String randomImg(String[] imgs) {
        Random random = new Random();
        int index = random.nextInt(imgs.length);
        return imgs[index];
    }

}
