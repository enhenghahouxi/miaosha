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

    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
