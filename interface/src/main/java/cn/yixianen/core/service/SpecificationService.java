package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.SpecEntity;
import cn.yixianen.core.pojo.specification.Specification;

/**
 * @author yixianen
 */
public interface SpecificationService {

    /**
     * 品牌高级查询
     * @param spec
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(Specification spec,Integer page,Integer rows);

    /**
     * 添加规格
     * @param specEntity
     */
    void add(SpecEntity specEntity);

    /**
     * 修改规格数据回显
     * @param id
     * @return
     */
    SpecEntity findOne(Long id);

    /**
     * 修改规格
     * @param specEntity
     */
    void update(SpecEntity specEntity);

    /**
     * 批量删除规格
     * @param ids
     */
    void delete(Long[] ids);
}
