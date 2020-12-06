package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.item.ItemCat;
import cn.yixianen.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/6 16:48
 */
@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
    @Reference
    private ItemCatService catService;

    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long parentId) {
        return catService.findByParentId(parentId);
    }
    @RequestMapping("/findOne")
    public ItemCat findOne(Long id){
        return catService.findOne(id);
    }

}
