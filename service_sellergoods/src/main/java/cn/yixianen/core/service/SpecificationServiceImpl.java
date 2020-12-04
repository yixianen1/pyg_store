package cn.yixianen.core.service;

import cn.yixianen.core.dao.specification.SpecificationDao;
import cn.yixianen.core.dao.specification.SpecificationOptionDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.SpecEntity;
import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.pojo.specification.SpecificationOption;
import cn.yixianen.core.pojo.specification.SpecificationOptionQuery;
import cn.yixianen.core.pojo.specification.SpecificationQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @author yixianen
 */
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationDao specDao;
    @Autowired
    private SpecificationOptionDao optionDao;


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

    @Override
    public void add(SpecEntity specEntity) {
        //添加规格对象
        specDao.insertSelective(specEntity.getSpecification());
        //添加规格属性
        if (specEntity.getSpecificationOptionList() != null) {
            for (SpecificationOption option : specEntity.getSpecificationOptionList()) {
                //设置规格属性选项外键
                option.setSpecId(specEntity.getSpecification().getId());
                optionDao.insertSelective(option);
            }
        }
    }

    @Override
    public SpecEntity findOne(Long id) {
        //根据规格id查询规格对象
        Specification specification = specDao.selectByPrimaryKey(id);
        //根据规格id查询规格选项集合对象
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecificationOption> specificationOptionList = optionDao.selectByExample(query);
        //将规格对象和规格选项集合对象封装到要返回的实体
        SpecEntity specEntity = new SpecEntity();
        specEntity.setSpecification(specification);
        specEntity.setSpecificationOptionList(specificationOptionList);
        return specEntity;
    }

    @Override
    public void update(SpecEntity specEntity) {
        //更新规格
        specDao.updateByPrimaryKeySelective(specEntity.getSpecification());
        //删除规格选项集合列表
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        SpecificationOptionQuery.Criteria criteria = query.createCriteria();
        criteria.andSpecIdEqualTo(specEntity.getSpecification().getId());
        optionDao.deleteByExample(query);
        //插入新的规格选项集合列表
        if (specEntity.getSpecificationOptionList()!=null){
            for (SpecificationOption option : specEntity.getSpecificationOptionList()) {
                option.setSpecId(specEntity.getSpecification().getId());
                optionDao.insertSelective(option);
            }

        }

    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null){
            for (Long id : ids) {
                specDao.deleteByPrimaryKey(id);
                SpecificationOptionQuery query = new SpecificationOptionQuery();
                SpecificationOptionQuery.Criteria criteria = query.createCriteria();
                criteria.andSpecIdEqualTo(id);
                optionDao.deleteByExample(query);
            }
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return specDao.selectOptionList();
    }


}
