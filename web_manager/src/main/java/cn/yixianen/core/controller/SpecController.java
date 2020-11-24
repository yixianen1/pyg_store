package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.service.SpecificationService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
