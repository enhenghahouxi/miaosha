package com.miaosha.miaosha.controller;

import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.domain.OrderInfo;
import com.miaosha.miaosha.result.CodeMsg;
import com.miaosha.miaosha.result.Result;
import com.miaosha.miaosha.service.GoodsService;
import com.miaosha.miaosha.service.OrderService;
import com.miaosha.miaosha.vo.GoodsVo;
import com.miaosha.miaosha.vo.OrderDetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/12 20:22
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private OrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
                                      @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order  = orderService.getOrderById(orderId);
        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setGoods(goods);
        vo.setOrderInfo(order);

        return Result.success(vo);
    }

}
