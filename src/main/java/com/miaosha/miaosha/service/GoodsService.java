package com.miaosha.miaosha.service;

import com.miaosha.miaosha.dao.GoodsDao;
import com.miaosha.miaosha.dao.UserDao;
import com.miaosha.miaosha.domain.Goods;
import com.miaosha.miaosha.domain.MiaoshaGoods;
import com.miaosha.miaosha.domain.User;
import com.miaosha.miaosha.vo.GoodsVo;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/18 21:31
 */
@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.ListGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        //卖超情况：当库存还有1时，同时来了来了两个线程，库存同时减两个1，减成-1。解决方法：数据库判断库存大于0才进行减
        int ret = goodsDao.reduceStock(g);
        return ret > 0;
    }

    public void resetStock(List<GoodsVo> goodsList) {
        for(GoodsVo goods : goodsList ) {
            MiaoshaGoods g = new MiaoshaGoods();
            g.setGoodsId(goods.getId());
            g.setStockCount(goods.getStockCount());
            goodsDao.resetStock(g);
        }
    }
}
