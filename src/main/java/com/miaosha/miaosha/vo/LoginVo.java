package com.miaosha.miaosha.vo;

import org.hibernate.validator.constraints.Length;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.validation.constraints.NotNull;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 16:38
 */
public class LoginVo {

    @NotNull
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
