package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.good.Brand;
import cn.yixianen.core.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yixianen
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;
    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        List<Brand> brandList = brandService.findAll();
        return brandList;
    }

    /**
     *
     * @param page 当前页
     * @param rows 每页展示数据条数
     * @return
     */
    @RequestMapping("/findByPage")
    public PageResult findByPage(Integer page,Integer rows){
        System.out.println("controller==="+page+"==="+rows);
        return brandService.findByPage(page, rows);
    }
}
