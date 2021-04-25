package com.miaosha.miaosha.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/24 21:04
 */
@Configuration
public class MQConfig {

    public static final String QUEUE = "queue";

    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";

    public static final String FANOUT_EXCHANGE = "fanoutExchange";

    public static final String HEADER_QUEUE = "header.queue";
    public static final String HEADERS_EXCHANGE = "headersExchange";

    public static final String MIAOSHA_QUEUE = "miaosha.queue";

    @Bean
    public Queue miaoshaQueue() {
        return new Queue(MIAOSHA_QUEUE, true);//消息队列名
    }

    /**
     * 交换机模式 Direct模式
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);//消息队列名
    }

    /**
     * 交换机模式 topic模式
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);//消息队列名
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);//消息队列名
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);//topic交换机名
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.key1");//和binding相匹配的routingKey
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");//和binding相匹配的routingKey
    }

    /**
     * 交换机模式 fanout模式
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);//fanout交换机名
    }

    @Bean
    public Binding FanoutBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());//fanout不需要routingKey
    }

    @Bean
    public Binding FanoutBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());//fanout不需要routingKey
    }

    /**
     * 交换机模式 header模式
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);//header交换机名
    }

    @Bean
    public Queue headerQueue1() {
        return new Queue(HEADER_QUEUE, true);//消息队列名
    }

    @Bean
    public Binding headerBinding1() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headerQueue1()).to(headersExchange()).whereAll(map).match();//header的map匹配上的就发送
    }

}
