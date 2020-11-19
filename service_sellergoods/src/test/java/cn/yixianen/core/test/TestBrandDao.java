package cn.yixianen.core.test;

import cn.yixianen.core.dao.good.BrandDao;
import cn.yixianen.core.pojo.good.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//配置spring测试环境
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class TestBrandDao {
    @Autowired
    private BrandDao brandDao;

    @Test
    public void testFindBrandById() {
        Brand brand = brandDao.selectByPrimaryKey(1L);
        System.out.println("==========" + brand);
    }

    @Test
    public void testFindBrandAll() {
        List<Brand> brands = brandDao.selectByExample(null);
//        System.out.println("==========" + brands);
        for (Brand brand : brands) {
            System.out.println(brand);
        }
    }
}
