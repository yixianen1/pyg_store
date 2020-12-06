package cn.yixianen.core.service;

import cn.yixianen.core.pojo.item.ItemCat;

import java.util.List;

/**
 * 分类查询
 *
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/6 16:52
 */
public interface ItemCatService {
    /**
     * 根据父id查询
     *
     * @param parentId 父id
     * @return
     */
    List<ItemCat> findByParentId(Long parentId);

    /**
     * 根据id查询实体
     * @param id 分类主键
     * @return
     */
    ItemCat findOne(Long id);
}
