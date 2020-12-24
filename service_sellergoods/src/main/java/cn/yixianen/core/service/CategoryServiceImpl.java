package cn.yixianen.core.service;

import cn.yixianen.core.dao.ad.ContentCategoryDao;
import cn.yixianen.core.pojo.ad.ContentCategory;
import cn.yixianen.core.pojo.ad.ContentCategoryQuery;
import cn.yixianen.core.pojo.entity.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/24 20:13
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ContentCategoryDao categoryDao;

    @Override
    public List<ContentCategory> findAll() {
        return categoryDao.selectByExample(null);
    }

    @Override
    public PageResult findByPage(ContentCategory category, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        ContentCategoryQuery query = new ContentCategoryQuery();
        ContentCategoryQuery.Criteria criteria = query.createCriteria();
        if (category != null) {
            if (category.getName() != null && !"".equals(category.getName())) {
                criteria.andNameLike("%" + category.getName() + "%");
            }
        }
        Page<ContentCategory> categoryList = (Page<ContentCategory>) categoryDao.selectByExample(query);
        return new PageResult(categoryList.getTotal(), categoryList.getResult());
    }

    @Override
    public void add(ContentCategory category) {
        categoryDao.insertSelective(category);
    }

    @Override
    public ContentCategory findOne(Long id) {
        return categoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContentCategory category) {
        categoryDao.updateByPrimaryKeySelective(category);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                categoryDao.deleteByPrimaryKey(id);
            }
        }
    }
}
