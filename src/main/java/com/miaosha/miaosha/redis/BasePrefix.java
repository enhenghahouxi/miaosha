package com.miaosha.miaosha.redis;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/19 11:47
 */
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix (String prefix) {
        this(0, prefix);
    }

    public BasePrefix (int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }

}
