package com.miaosha.miaosha.rabbitmq;

import com.miaosha.miaosha.domain.MiaoshaOrder;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.redis.RedisService;
import com.miaosha.miaosha.result.CodeMsg;
import com.miaosha.miaosha.result.Result;
import com.miaosha.miaosha.service.GoodsService;
import com.miaosha.miaosha.service.MiaoshaService;
import com.miaosha.miaosha.service.OrderService;
import com.miaosha.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/24 21:03
 */
@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private MiaoshaService miaoshaService;

    @Resource
    private RedisService redisService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)//监听的是这个消息队列
    public void receive(String message) {
        log.info("receive message" + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }

        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return;
        }

        //进行秒杀，减库存，下订单，写入秒杀订单
        miaoshaService.miaosha(user, goods);
        return;

    }

//    @RabbitListener(queues = MQConfig.QUEUE)//监听的是这个消息队列
//    public void receive(String message) {
//        log.info("receive message" + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)//监听的是这个消息队列
//    public void receiveTopic1(String message) {
//        log.info("topic queue1 message" + message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)//监听的是这个消息队列
//    public void receiveTopic2(String message) {
//        log.info("topic queue2 message" + message);
//    }
//
//    @RabbitListener(queues = MQConfig.HEADER_QUEUE)//监听的是这个消息队列
//    public void receiveHeader(byte[] message) {
//        log.info("header queue message" +new String(message));
//    }

}
