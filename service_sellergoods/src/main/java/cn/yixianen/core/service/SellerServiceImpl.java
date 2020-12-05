package cn.yixianen.core.service;

import cn.yixianen.core.dao.seller.SellerDao;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.seller.Seller;
import cn.yixianen.core.pojo.seller.SellerQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yixianen
 */
@Service
@Transactional
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(Seller seller) {
        seller.setStatus("0");
        seller.setCreateTime(new Date());
        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult search(Seller seller, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        SellerQuery query = new SellerQuery();
        SellerQuery.Criteria criteria = query.createCriteria();
        if (seller != null) {
            if (seller.getStatus() != null && !"".equals(seller.getStatus())) {
                criteria.andStatusEqualTo(seller.getStatus());
            }
            if (seller.getNickName() != null && !"".equals(seller.getNickName())) {
                criteria.andNickNameLike("%" + seller.getNickName() + "%");
            }
            if (seller.getName() != null && !"".equals(seller.getName())) {
                criteria.andNameLike("%" + seller.getName() + "%");
            }
        }
        Page<Seller> sellerList = (Page<Seller>) sellerDao.selectByExample(query);
        return new PageResult(sellerList.getTotal(), sellerList.getResult());
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerDao.updateByPrimaryKeySelective(seller);
    }

    @Override
    public Seller findOne(String id) {
        return sellerDao.selectByPrimaryKey(id);
    }
}
