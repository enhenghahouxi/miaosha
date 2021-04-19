package com.miaosha.miaosha.domain;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/18 21:29
 */
public class User {

    private int id;

    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
