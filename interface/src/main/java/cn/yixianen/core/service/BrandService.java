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

    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 数据回显
     * @param id
     * @return
     */
    Brand findOne(Long id);

    /**
     * 更新数据
     * @param brand
     */
    void update(Brand brand);

    /**
     * 批量删除
     * @param ids
     */
    void delete(Long[] ids);
}
