package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.seller.Seller;
import cn.yixianen.core.service.SellerService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yixianen
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    private SellerService sellerService;
    @RequestMapping("/search")
    public PageResult search(@RequestBody Seller seller,Integer page,Integer rows){
        return sellerService.search(seller, page, rows);
    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status){
        try {
            sellerService.updateStatus(sellerId, status);
            return new Result(true, "修改商家状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改商家状态失败");
        }
    }
    @RequestMapping("/findOne")
    public Seller findOne(String id){
        return sellerService.findOne(id);
    }
}
