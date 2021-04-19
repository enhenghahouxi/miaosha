package com.miaosha.miaosha.exception;

import com.miaosha.miaosha.result.CodeMsg;

/**
 * 全局异常
 *
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 20:33
 */
public class GlobalException extends RuntimeException{

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

    public void setCm(CodeMsg cm) {
        this.cm = cm;
    }

}
