package com.miaosha.miaosha.access;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/26 20:06
 */
public @interface AccessLimit {

    int seconds();
    int maxCount();
    boolean needLogin() default true;

}
