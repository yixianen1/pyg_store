package cn.yixianen.core.service;

import cn.yixianen.core.pojo.ad.ContentCategory;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.good.Brand;

import java.util.List;
import java.util.Map;

/**
 * @author yixianen
 */
public interface CategoryService {
    /**
     * 查询全部品牌
     * @return
     */
    List<ContentCategory> findAll();

    /**
     *分页查询品牌
     * @param page 当前页
     * @param rows 每页展示条数
     * @return
     */
    PageResult findByPage(ContentCategory category,Integer page,Integer rows);

    /**
     * 添加品牌
     * @param category
     */
    void add(ContentCategory category);

    /**
     * 数据回显
     * @param id
     * @return
     */
    ContentCategory findOne(Long id);

    /**
     * 更新数据
     * @param category
     */
    void update(ContentCategory category);

    /**
     * 批量删除
     * @param ids
     */
    void delete(Long[] ids);

}
