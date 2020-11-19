package cn.yixianen.core.service;

import cn.yixianen.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author yixianen
 */
public interface BrandService {
    /**
     * 查询全部品牌
     * @return
     */
    List<Brand> findAll();
}
