package cn.yixianen.core.service;

import cn.yixianen.core.dao.specification.SpecificationDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.pojo.specification.SpecificationQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author yixianen
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationDao specDao;

    /**
     * @param spec
     * @param page 当前页
     * @param rows 每页条数
     * @return
     */
    @Override
    public PageResult findPage(Specification spec, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        SpecificationQuery query = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = query.createCriteria();
        if (spec != null) {
            if (spec.getSpecName() != null && !"".equals(spec.getSpecName())) {
                criteria.andSpecNameLike("%" + spec.getSpecName() + "%");

            }
        }
        Page<Specification> specList = (Page<Specification>) specDao.selectByExample(query);
        return new PageResult(specList.getTotal(), specList.getResult());
    }
}
