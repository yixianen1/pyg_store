package cn.yixianen.core.service;

import cn.yixianen.core.dao.good.BrandDao;
import cn.yixianen.core.dao.good.GoodsDao;
import cn.yixianen.core.dao.good.GoodsDescDao;
import cn.yixianen.core.dao.item.ItemCatDao;
import cn.yixianen.core.dao.item.ItemDao;
import cn.yixianen.core.dao.seller.SellerDao;
import cn.yixianen.core.pojo.entity.GoodsEntity;
import cn.yixianen.core.pojo.good.Brand;
import cn.yixianen.core.pojo.item.Item;
import cn.yixianen.core.pojo.item.ItemCat;
import cn.yixianen.core.pojo.seller.Seller;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/8 15:47
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsDescDao descDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemCatDao catDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(GoodsEntity goodsEntity) {
        /**
         * 1. 保存商品对象
         */
        //刚添加的商品状态默认为0 未审核
        goodsEntity.getGoods().setAuditStatus("0");
        goodsDao.insertSelective(goodsEntity.getGoods());

        /**
         * 2. 保存商品详情对象
         */
        //商品主键作为商品详情的主键
        goodsEntity.getGoodsDesc().setGoodsId(goodsEntity.getGoods().getId());
        descDao.insertSelective(goodsEntity.getGoodsDesc());

        /**
         * 3. 保存库存集合对象
         */
        insertItem(goodsEntity);
    }

    /**
     * 使用goodsEntity实体类中的数据初始化, item库存对象中的属性值
     *
     * @param goodsEntity
     * @param item
     * @return
     */
    private Item setItemValue(GoodsEntity goodsEntity, Item item) {
        //商品id
        item.setGoodsId(goodsEntity.getGoods().getId());
        //创建时间
        item.setCreateTime(new Date());
        //更新时间
        item.setUpdateTime(new Date());
        //库存状态, 默认为0, 未审核
        item.setStatus("0");
        //分类id, 库存使用商品的第三级分类最为库存分类
        item.setCategoryid(goodsEntity.getGoods().getCategory3Id());
        //分类名称
        ItemCat itemCat = catDao.selectByPrimaryKey(goodsEntity.getGoods().getCategory3Id());
        item.setCategory(itemCat.getName());
        //品牌名称
        Brand brand = brandDao.selectByPrimaryKey(goodsEntity.getGoods().getBrandId());
        item.setBrand(brand.getName());
        //卖家名称

        Seller seller = sellerDao.selectByPrimaryKey(goodsEntity.getGoods().getSellerId());
        System.out.println("卖家名称" + seller.getName());
        System.out.println("卖家id" + goodsEntity.getGoods().getSellerId());
        item.setSeller(seller.getName());


        //示例图片
        String itemImages = goodsEntity.getGoodsDesc().getItemImages();
        List<Map> maps = JSON.parseArray(itemImages, Map.class);
        if (maps != null && maps.size() > 0) {
            String url = String.valueOf(maps.get(0).get("url"));
            item.setImage(url);
        }
        return item;
    }

    /**
     * 保存库存数据
     */
    public void insertItem(GoodsEntity goodsEntity) {
        if ("1".equals(goodsEntity.getGoods().getIsEnableSpec())) {
            //勾选规格复选框, 有库存数据
            if (goodsEntity.getItemList() != null) {
                for (Item item : goodsEntity.getItemList()) {
                    //库存标题, 由商品名 + 规格组成具体的库存标题, 供消费者搜索使用, 可以搜索的更精确
                    String title = goodsEntity.getGoods().getGoodsName();
                    //从库存对象中获取前端传入的json格式规格字符串, 例如: {"机身内存":"16G","网络":"联通3G"}
                    String specJsonStr = item.getSpec();
                    //将json格式字符串转换成对象
                    Map speMap = JSON.parseObject(specJsonStr, Map.class);
                    //获取map中的value集合
                    Collection<String> values = speMap.values();
                    for (String value : values) {
                        title += " " + value;
                    }
                    item.setTitle(title);

                    //设置库存对象的属性值
                    setItemValue(goodsEntity, item);

                    itemDao.insertSelective(item);
                }
            }
        } else {
            //没有勾选复选框, 没有库存数据, 但是我们需要初始化一条, 不然前端有可能报错
            Item item = new Item();
            //价格
            item.setPrice(new BigDecimal("99999999999"));
            //库存量
            item.setNum(0);
            //初始化规格
            item.setSpec("{}");
            //标题
            item.setTitle(goodsEntity.getGoods().getGoodsName());
            //设置库存对象的属性值
            setItemValue(goodsEntity, item);
            itemDao.insertSelective(item);
        }
    }

}
