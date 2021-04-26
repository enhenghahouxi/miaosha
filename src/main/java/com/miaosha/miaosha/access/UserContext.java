package com.miaosha.miaosha.access;

import com.miaosha.miaosha.domain.MiaoshaUser;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/26 20:17
 */
public class UserContext {

    private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user){
        userHolder.set(user);
    }

    public static MiaoshaUser getUser(){
        return userHolder.get();
    }

}
