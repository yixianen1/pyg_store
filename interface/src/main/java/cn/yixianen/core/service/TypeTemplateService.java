package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.template.TypeTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author yixianen
 */
public interface TypeTemplateService {
    /**
     * 模板高级查询
     *
     * @param typeTemplate
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(TypeTemplate typeTemplate, Integer page, Integer rows);

    /**
     * 查询实体
     * @param id
     * @return
     */
    TypeTemplate findOne(Long id);

    /**
     * 添加实体
     * @param typeTemplate
     */
    void add(TypeTemplate typeTemplate);

    /**
     * 更新实体
     * @param typeTemplate
     * @return
     */
    void update(TypeTemplate typeTemplate);

    /**
     * 根据id删除
     * @param ids
     * @return
     */
    void delete(Long[] ids);

    /**
     * 根据模板id查询规格列表
     * @param id
     * @return
     */
    List<Map> findBySpecList(Long id);
}
