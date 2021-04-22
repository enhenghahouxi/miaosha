package com.miaosha.miaosha.service;

import com.miaosha.miaosha.dao.GoodsDao;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.domain.OrderInfo;
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

    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //进行秒杀，
        //秒杀商品表减库存，下订单，写入秒杀订单
        goodsService.reduceStock(goods);
        //生成order_info miaosha_order
        return orderService.createOrder(user, goods);
    }
}
