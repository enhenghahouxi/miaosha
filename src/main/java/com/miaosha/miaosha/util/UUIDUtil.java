package com.miaosha.miaosha.util;

import java.util.UUID;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 20:50
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
