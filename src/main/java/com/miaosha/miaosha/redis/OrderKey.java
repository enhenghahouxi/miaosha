package com.miaosha.miaosha.redis;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 11:56
 */
public class OrderKey extends BasePrefix {

    private OrderKey (String prefix) {
        super(prefix);
    }

}
