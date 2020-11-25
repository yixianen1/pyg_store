package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.template.TypeTemplate;

/**
 * @author yixianen
 */
public interface TypeTemplateService {
    /**
     * 模板高级查询
     * @param typeTemplate
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(TypeTemplate typeTemplate, Integer page, Integer rows);
}
