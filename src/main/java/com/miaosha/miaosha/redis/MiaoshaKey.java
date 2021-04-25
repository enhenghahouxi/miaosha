package com.miaosha.miaosha.redis;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/25 8:15
 */
public class MiaoshaKey extends BasePrefix{

    private MiaoshaKey(String prefix) {
        super(prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey("go");

}
