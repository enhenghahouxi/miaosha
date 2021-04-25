package com.miaosha.miaosha.controller;

import com.miaosha.miaosha.domain.User;
import com.miaosha.miaosha.rabbitmq.MQSender;
import com.miaosha.miaosha.redis.KeyPrefix;
import com.miaosha.miaosha.redis.UserKey;
import com.miaosha.miaosha.result.Result;
import com.miaosha.miaosha.redis.RedisService;
import com.miaosha.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/12 20:22
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    @Autowired
    private MQSender sender;

//    @RequestMapping("/mq/header")
//    @ResponseBody
//    public Result<String> header() {
//        sender.sendHeader("hello,enhegnhahouxi");
//        return Result.success("hello");
//    }
//
//    @RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> fanout() {
//        sender.sendFanout("hello,enhegnhahouxi");
//        return Result.success("hello");
//    }
//
//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> topic() {
//        sender.sendTopic("hello,enhegnhahouxi");
//        return Result.success("hello");
//    }
//
//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//        sender.send("hello,enhegnhahouxi");
//        return Result.success("hello");
//    }

    @RequestMapping("/hello/themaleaf")
    public String themaleaf(Model model) {
        model.addAttribute("name", "enhenghahouxi");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User user = redisService.get(UserKey.getById, "" + 1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User(1, "11111");
        Boolean v1 = redisService.set(UserKey.getById, "" + 1, user);
        return Result.success(v1);
    }

}
