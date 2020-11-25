package cn.yixianen.core.service;

import cn.yixianen.core.dao.template.TypeTemplateDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.template.TypeTemplate;
import cn.yixianen.core.pojo.template.TypeTemplateQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 模板
 *
 * @author yixianen
 */

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TypeTemplateDao templateDao;

    @Override
    public PageResult findPage(TypeTemplate typeTemplate, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TypeTemplateQuery query = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = query.createCriteria();
        if (typeTemplate != null) {
            if (typeTemplate.getName() != null && !"".equals(typeTemplate.getName())) {
                criteria.andNameLike("%"+ typeTemplate.getName()+"%");
            }
        }
        Page<TypeTemplate> templateList = (Page<TypeTemplate>) templateDao.selectByExample(query);

        return new PageResult(templateList.getTotal(),templateList.getResult());
    }
}
