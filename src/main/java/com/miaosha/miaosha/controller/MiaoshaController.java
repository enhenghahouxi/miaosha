package com.miaosha.miaosha.controller;

import com.miaosha.miaosha.domain.MiaoshaOrder;
import com.miaosha.miaosha.domain.MiaoshaUser;
import com.miaosha.miaosha.domain.OrderInfo;
import com.miaosha.miaosha.result.CodeMsg;
import com.miaosha.miaosha.service.GoodsService;
import com.miaosha.miaosha.service.MiaoshaService;
import com.miaosha.miaosha.service.OrderService;
import com.miaosha.miaosha.vo.GoodsVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/do_miaosha")
    public String list(Model model, MiaoshaUser user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEAT_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //进行秒杀，减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);

        return "order_detail";
    }

}
