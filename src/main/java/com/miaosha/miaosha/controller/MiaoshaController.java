package com.miaosha.miaosha.controller;

import com.miaosha.miaosha.domain.MiaoshaOrder;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.domain.OrderInfo;
import com.miaosha.miaosha.result.CodeMsg;
import com.miaosha.miaosha.result.Result;
import com.miaosha.miaosha.service.GoodsService;
import com.miaosha.miaosha.service.MiaoshaService;
import com.miaosha.miaosha.service.OrderService;
import com.miaosha.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/12 20:22
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @Resource
    private MiaoshaService miaoshaService;

    /**
     * 自己电脑
     * 没用redis: QPS 700
     * 用redis: QPS 1150
     * 5000 * 10
     */
    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //判断库存
        //卖超情况:10个商品，同时两个请求，判断是否秒杀到，就都能够秒杀，然后一个用户同时秒杀到两个, 解决方法：数据库加用户ID和商品ID的联合索引
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEAT_MIAOSHA);
        }
        //进行秒杀，减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        return Result.success(orderInfo);
    }

}
