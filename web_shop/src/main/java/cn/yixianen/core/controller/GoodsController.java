package cn.yixianen.core.controller;


import cn.yixianen.core.pojo.entity.GoodsEntity;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 * @author yixianen
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @RequestMapping("/add")
    public Result add(@RequestBody GoodsEntity goodsEntity) {
        try {
            //获取登录用户名
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            //设置这个商品添加的用户名, 也就是卖家id
            goodsEntity.getGoods().setSellerId(userName);

            goodsService.add(goodsEntity);
            return new Result(true, "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败!");
        }
    }
}
