package com.miaosha.miaosha.vo;

import com.miaosha.miaosha.domain.MiaoshaUser;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/23 22:22
 */
public class GoodsDetailVo {

    private int miaoshaStatus;
    private int remainSeconds;
    private GoodsVo goods;
    private MiaoshaUser user;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

}
