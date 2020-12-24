package cn.yixianen.core.service;

import cn.yixianen.core.pojo.ad.Content;
import cn.yixianen.core.pojo.ad.ContentCategory;
import cn.yixianen.core.pojo.entity.PageResult;

import java.util.List;

/**
 * @author yixianen
 */
public interface ContentService {
    /**
     * 查询全部品牌
     * @return
     */
    List<Content> findAll();

    /**
     *分页查询品牌
     * @param page 当前页
     * @param rows 每页展示条数
     * @return
     */
    PageResult findByPage(Content content,Integer page,Integer rows);

    /**
     * 添加品牌
     * @param content
     */
    void add(Content content);

    /**
     * 数据回显
     * @param id
     * @return
     */
    Content findOne(Long id);

    /**
     * 更新数据
     * @param content
     */
    void update(Content content);

    /**
     * 批量删除
     * @param ids
     */
    void delete(Long[] ids);

}
