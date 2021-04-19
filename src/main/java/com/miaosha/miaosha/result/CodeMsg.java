package com.miaosha.miaosha.result;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/18 21:34
 */
public class CodeMsg {

    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(200,"success");

    public static CodeMsg SERVER_ERROR = new CodeMsg(400,"服务端异常");


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
