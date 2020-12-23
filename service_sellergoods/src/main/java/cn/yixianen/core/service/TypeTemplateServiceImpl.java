package cn.yixianen.core.service;

import cn.yixianen.core.dao.specification.SpecificationOptionDao;
import cn.yixianen.core.dao.template.TypeTemplateDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.specification.SpecificationOption;
import cn.yixianen.core.pojo.specification.SpecificationOptionQuery;
import cn.yixianen.core.pojo.template.TypeTemplate;
import cn.yixianen.core.pojo.template.TypeTemplateQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private SpecificationOptionDao optionDao;

    @Override
    public PageResult findPage(TypeTemplate typeTemplate, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TypeTemplateQuery query = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = query.createCriteria();
        if (typeTemplate != null) {
            if (typeTemplate.getName() != null && !"".equals(typeTemplate.getName())) {
                criteria.andNameLike("%" + typeTemplate.getName() + "%");
            }
        }
        Page<TypeTemplate> templateList = (Page<TypeTemplate>) templateDao.selectByExample(query);

        return new PageResult(templateList.getTotal(), templateList.getResult());
    }

    @Override
    public TypeTemplate findOne(Long id) {
        return templateDao.selectByPrimaryKey(id);
    }

    @Override
    public void add(TypeTemplate typeTemplate) {
        templateDao.insertSelective(typeTemplate);
    }

    @Override
    public void update(TypeTemplate typeTemplate) {
        templateDao.updateByPrimaryKey(typeTemplate);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                templateDao.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public List<Map> findBySpecList(Long id) {
        TypeTemplate typeTemplate = templateDao.selectByPrimaryKey(id);
        List<Map> maps = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
        if (maps != null) {
            for (Map map : maps) {
                //查询规格选项列表
                SpecificationOptionQuery query = new SpecificationOptionQuery();
                SpecificationOptionQuery.Criteria criteria = query.createCriteria();
                criteria.andSpecIdEqualTo(Long.parseLong(String.valueOf(map.get("id"))));
                List<SpecificationOption> specificationOptions = optionDao.selectByExample(query);
                map.put("options", specificationOptions);
            }
        }
        return maps;
    }
}
