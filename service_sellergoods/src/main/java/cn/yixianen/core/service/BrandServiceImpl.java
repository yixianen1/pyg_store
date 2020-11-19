package cn.yixianen.core.service;

import cn.yixianen.core.dao.good.BrandDao;
import cn.yixianen.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yixianen
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;
    @Override
    public List<Brand> findAll() {
        return brandDao.selectByExample(null);
    }
}
