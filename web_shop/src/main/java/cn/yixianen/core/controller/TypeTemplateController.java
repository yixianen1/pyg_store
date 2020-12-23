package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.entity.PageResult;
import cn.yixianen.core.pojo.entity.Result;
import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.pojo.template.TypeTemplate;
import cn.yixianen.core.service.SpecificationService;
import cn.yixianen.core.service.TypeTemplateService;
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
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    @Reference
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id){
        return typeTemplateService.findOne(id);

    }
    @RequestMapping("/findBySpecList")
    public List<Map> findBySpecList(Long id){
        return typeTemplateService.findBySpecList(id);
    }

}
