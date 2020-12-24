package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.ad.Content;
import cn.yixianen.core.pojo.ad.ContentCategory;
import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.service.CategoryService;
import cn.yixianen.core.service.ContentService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yixianen
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Reference
    private ContentService contentService;

    @RequestMapping("/findAll")
    public List<Content> findAll() {
        List<Content> list = contentService.findAll();
        return list;
    }


    /**
     * 添加品牌
     *
     * @param content
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Content content) {
        try {
            contentService.add(content);
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
    public Content findOne(Long id) {
        Content one = contentService.findOne(id);
        return one;
    }

    /**
     * 保存更新
     *
     * @param content
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Content content) {
        try {
            contentService.update(content);
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
            contentService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 高级查询
     *
     * @param content
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody Content content, Integer page, Integer rows) {
        PageResult pageResult = contentService.findByPage(content, page, rows);
        return pageResult;
    }


}
