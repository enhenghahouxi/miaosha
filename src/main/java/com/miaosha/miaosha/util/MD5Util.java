package com.miaosha.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 15:49
 */
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    /**
     * md5加密
     */
    public  static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 输入密码进行第一次加密
     * 固定salt
     */
    public  static String inputPassFormPass(String inputpass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputpass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 第一次加密后的密码进行第二次加密
     * 数据库salt，和固定salt不同
     */
    public  static String formPassToDBPass(String formpass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formpass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    /**
     * 用户输入密码直接进行二次加密
     */
    public  static String inputPassToDBPass(String inputpass, String saltDB) {
        String formPass = inputPassFormPass(inputpass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDBPass("123456", "1a2b3c4d"));
    }
}
