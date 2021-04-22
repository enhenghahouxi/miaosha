package com.miaosha.miaosha.controller;

import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.result.Result;
import com.miaosha.miaosha.service.GoodsService;
import com.miaosha.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/12 20:22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
        return Result.success(user);
    }

}
