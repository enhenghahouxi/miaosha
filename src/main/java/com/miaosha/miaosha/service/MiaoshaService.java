package com.miaosha.miaosha.service;

import com.miaosha.miaosha.dao.GoodsDao;
import com.miaosha.miaosha.domain.MiaoshaOrder;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.domain.OrderInfo;
import com.miaosha.miaosha.redis.MiaoshaKey;
import com.miaosha.miaosha.redis.RedisService;
import com.miaosha.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/18 21:31
 */
@Service
public class MiaoshaService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private RedisService redisService;

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //进行秒杀，
        //秒杀商品表减库存，下订单，写入秒杀订单
        boolean success = goodsService.reduceStock(goods);
        if (success) {
            //生成order_info miaosha_order
            return orderService.createOrder(user, goods);
        } else {
            //商品卖完了，放入redis
            setGoodsOver(goods.getId());
            return null;
        }
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        if (order != null) {//秒杀成功
            return order.getOrderId();
        }else {
            //判断商品是否卖完；
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;//秒杀失败，商品已经卖完
            }else {
                return 0;//排队中
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisService.set(MiaoshaKey.isGoodsOver, "" + goodsId, true);
    }

    private boolean getGoodsOver(long goodsId) {
        return  redisService.exist(MiaoshaKey.isGoodsOver, "" + goodsId);
    }

    public void reset(List<GoodsVo> goodsList) {
        goodsService.resetStock(goodsList);
        orderService.deleteOrders();
    }
}
