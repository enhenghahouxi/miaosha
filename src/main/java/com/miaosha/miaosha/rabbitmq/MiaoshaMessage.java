package com.miaosha.miaosha.rabbitmq;

import com.miaosha.miaosha.domain.MiaoshaUser;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/24 23:01
 */
public class MiaoshaMessage {

    private MiaoshaUser user;
    private long goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
