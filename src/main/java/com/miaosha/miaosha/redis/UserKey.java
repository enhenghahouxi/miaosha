package com.miaosha.miaosha.redis;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 11:55
 */
public class UserKey extends BasePrefix{

    private UserKey (String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
