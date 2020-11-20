package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author yixianen
 */
public interface BrandService {
    /**
     * 查询全部品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 分页查询品牌
     * @param page 当前页
     * @param rows 每页展示条数
     * @return
     */
    PageResult findByPage(Integer page,Integer rows);
}
