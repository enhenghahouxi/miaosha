package com.miaosha.miaosha.vo;

import com.miaosha.miaosha.domain.OrderInfo;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/24 7:53
 */
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo orderInfo;

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
