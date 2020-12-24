package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.GoodsEntity;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.good.Goods;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/8 15:46
 */
public interface GoodsService {
    /**
     * 添加商品
     *
     * @param goodsEntity
     */
    void add(GoodsEntity goodsEntity);

    /**
     * 商品分页查询
     *
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    PageResult findPage(Goods goods, Integer page, Integer rows);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    GoodsEntity findOne(Long id);

    /**
     * 修改商品
     *
     * @param goodsEntity
     */
    void update(GoodsEntity goodsEntity);

    /**
     * 删除商品（逻辑删除）
     *
     * @param ids
     */
    void delete(Long[] ids);
}
