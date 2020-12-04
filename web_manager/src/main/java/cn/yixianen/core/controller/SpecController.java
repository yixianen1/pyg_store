package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.entity.SpecEntity;
import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.service.SpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 规格相关
 *
 * @author yixianen
 */
@RestController
@RequestMapping("/specification")
public class SpecController {
    @Reference
    private SpecificationService specService;

    @RequestMapping("/search")
    public PageResult search(@RequestBody Specification spec, Integer page, Integer rows) {
        PageResult result = specService.findPage(spec, page, rows);
        return result;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody SpecEntity specEntity) {
        try {
            specService.add(specEntity);
            return new Result(true, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "保存失败");
        }
    }

    @RequestMapping("/findOne")
    public SpecEntity findOne(Long id) {
        SpecEntity one = specService.findOne(id);
        return one;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody SpecEntity specEntity) {
        try {
            specService.update(specEntity);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            specService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return specService.selectOptionList();
    }
}












