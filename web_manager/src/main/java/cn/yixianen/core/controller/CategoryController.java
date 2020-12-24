package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.ad.ContentCategory;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.good.Brand;
import cn.yixianen.core.service.BrandService;
import cn.yixianen.core.service.CategoryService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author yixianen
 */
@RestController
@RequestMapping("/contentCategory")
public class CategoryController {
    @Reference
    private CategoryService categoryService;

    @RequestMapping("/findAll")
    public List<ContentCategory> findAll() {
        List<ContentCategory> list = categoryService.findAll();
        return list;
    }


    /**
     * 添加品牌
     *
     * @param category
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody ContentCategory category) {
        try {
            categoryService.add(category);
            return new Result(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "保存失败");
        }
    }

    /**
     * 根据id查询-数据回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public ContentCategory findOne(Long id) {
        ContentCategory one = categoryService.findOne(id);
        return one;
    }

    /**
     * 保存更新
     *
     * @param category
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ContentCategory category) {
        try {
            categoryService.update(category);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            categoryService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 高级查询
     *
     * @param category
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody ContentCategory category, Integer page, Integer rows) {
        PageResult pageResult = categoryService.findByPage(category, page, rows);
        return pageResult;
    }


}
