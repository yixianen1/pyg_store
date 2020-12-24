package cn.yixianen.core.controller;

import cn.yixianen.core.pojo.ad.Content;
import cn.yixianen.core.service.ContentService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yixianen
 * @version 1.0
 * @Date 2020/12/24 21:17
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Reference
    private ContentService contentService;

    @RequestMapping("/findByCategoryId")
    public List<Content> findByCategoryId(Long categoryId) {
        return contentService.findByCategoryId(categoryId);
    }
}
