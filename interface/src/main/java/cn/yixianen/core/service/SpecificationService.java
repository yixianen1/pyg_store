package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
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
}
