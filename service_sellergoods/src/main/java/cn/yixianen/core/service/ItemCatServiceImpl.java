package cn.yixianen.core.service;

import cn.yixianen.core.dao.item.ItemCatDao;
import cn.yixianen.core.pojo.item.ItemCat;
import cn.yixianen.core.pojo.item.ItemCatQuery;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/6 16:54
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatDao catDao;

    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        ItemCatQuery query = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = query.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return catDao.selectByExample(query);
    }
}
