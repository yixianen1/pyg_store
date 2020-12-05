package cn.yixianen.core.service;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.seller.Seller;

/**
 * @author yixianen
 */
public interface SellerService {
    /**
     * 商家注册
     *
     * @param seller
     */
    void add(Seller seller);

    /**
     * 高级查询
     *
     * @param seller
     * @param page
     * @param rows
     * @return
     */
    PageResult search(Seller seller, Integer page, Integer rows);

    /**
     * 商家审核
     * @param sellerId 商家id
     * @param status 商家状态 0未审核 1审核通过 2关闭商家
     * @return
     */
    void updateStatus(String sellerId, String status);

    /**
     * 查询单个实体 主键回显
     * @param id 商家id
     * @return
     */
    Seller findOne(String id);
}
