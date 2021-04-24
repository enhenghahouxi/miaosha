package com.miaosha.miaosha.dao;

import com.miaosha.miaosha.domain.Goods;
import com.miaosha.miaosha.domain.MiaoshaGoods;
import com.miaosha.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 嗯哼哈吼嘻
 * @Date 2021/4/20 22:04
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*, mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_Price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    public List<GoodsVo> ListGoodsVo();

    @Select("select g.*, mg.stock_count, mg.start_date, mg.end_date, mg.miaosha_Price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    public int reduceStock(MiaoshaGoods g);
}
