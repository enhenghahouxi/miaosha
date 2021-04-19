package com.miaosha.miaosha.redis;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 11:45
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
