package cn.yixianen.core.controller;


import cn.yixianen.core.pojo.entity.GoodsEntity;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.good.Goods;
import cn.yixianen.core.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理
 *
 * @author yixianen
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;


    @RequestMapping("/search")
    public PageResult search(@RequestBody Goods goods, Integer page, Integer rows) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        goods.setSellerId(userName);
        return goodsService.findPage(goods, page, rows);
    }

    @RequestMapping("/findOne")
    public GoodsEntity findOne(Long id) {
        return goodsService.findOne(id);
    }


    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            goodsService.delete(ids);
            return new Result(true, "删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败！");
        }
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] ids, String status){
        try {
            goodsService.updateStatus(ids, status);
            return new Result(true, "状态修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "状态修改失败");

        }
    }
}
