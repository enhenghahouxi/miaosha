package com.miaosha.miaosha.dao;

import com.miaosha.miaosha.domain.MiaoshaOrder;
import com.miaosha.miaosha.domain.OrderInfo;
import com.miaosha.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/20 22:04
 */
@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId,@Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, delivery_addr_id, goods_name, goods_count, goods_price, order_channel, status, create_date)" +
            "values(#{userId}, #{goodsId}, #{deliveryAddrId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id, goods_id, order_id)" +
            "values(#{userId}, #{goodsId}, #{orderId})")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
