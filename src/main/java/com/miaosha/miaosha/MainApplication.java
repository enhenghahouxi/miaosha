package com.miaosha.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/12 20:17
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(MainApplication.class, args);
    }

}
