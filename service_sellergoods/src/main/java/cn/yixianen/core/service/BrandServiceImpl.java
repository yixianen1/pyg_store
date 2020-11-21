package cn.yixianen.core.service;

import cn.yixianen.core.dao.good.BrandDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    @Override
    public PageResult findByPage(Integer page, Integer rows) {
        //利用分页助手实现分页
        PageHelper.startPage(page,rows);
        Page<Brand> brandList = (Page<Brand>) brandDao.selectByExample(null);
        System.out.println("service===="+brandList.getTotal());
        return new PageResult(brandList.getTotal(), brandList.getResult());
    }

    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public Brand findOne(Long id) {
        Brand brand = brandDao.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null){
            for (Long id : ids) {
                brandDao.deleteByPrimaryKey(id);
            }
        }
    }

}
