package com.miaosha.miaosha.rabbitmq;

import com.miaosha.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/24 21:02
 */
@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);


    @Autowired
    private AmqpTemplate amqpTemplate;


    public void sendMiaoshaMessage(MiaoshaMessage mm) {

        String msg = RedisService.beanToString(mm);
        log.info("send message" + msg);
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);//消息队列名，内容

    }

//    public void send(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send message" + msg);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);//消息队列名，内容
//    }
//
//    public void sendTopic(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send topic message" + msg);
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key1", msg + "1");//交换机名，routingKey， 内容
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE, "topic.key2", msg + "2");//交换机名，routingKey， 内容
//    }
//
//    public void sendFanout(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send fanout message" + msg);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE, "", msg);//交换机名， 内容
//    }
//
//    public void sendHeader(Object message) {
//        String msg = RedisService.beanToString(message);
//        log.info("send header message" + msg);
//        MessageProperties properties = new MessageProperties();
//        properties.setHeader("header1", "value1");
//        properties.setHeader("header2", "value2");
//        Message obj = new Message(msg.getBytes(), properties);
//        amqpTemplate.convertAndSend(MQConfig.HEADERS_EXCHANGE, "", obj);//交换机名， 内容
//    }

}
