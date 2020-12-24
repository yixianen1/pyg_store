package cn.yixianen.core.service;

import cn.yixianen.core.dao.ad.ContentDao;
import cn.yixianen.core.pojo.ad.Content;
import cn.yixianen.core.pojo.ad.ContentQuery;
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
 * @Date 2020/12/24 20:35
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Override
    public List<Content> findAll() {
        return contentDao.selectByExample(null);
    }

    @Override
    public PageResult findByPage(Content content, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        ContentQuery contentQuery = new ContentQuery();
        ContentQuery.Criteria criteria = contentQuery.createCriteria();
        if (content.getTitle() != null && !"".equals(content.getTitle())) {
            criteria.andTitleLike("%" + content.getTitle() + "%");
        }
        Page<Content> contentList = (Page<Content>) contentDao.selectByExample(contentQuery);
        return new PageResult(contentList.getTotal(), contentList.getResult());
    }

    @Override
    public void add(Content content) {
        contentDao.insertSelective(content);
    }

    @Override
    public Content findOne(Long id) {
        return contentDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Content content) {
        contentDao.updateByPrimaryKeySelective(content);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                contentDao.deleteByPrimaryKey(id);
            }
        }
    }
}
