package cn.yixianen.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yixianen
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("/showName")
    public Map showName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map = new HashMap();
        map.put("username", name);
        return map;
    }
}
