package com.fufu.controller;

import com.fufu.entity.BlogVisitor;
import com.fufu.service.RestfulService;
import com.fufu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@EnableTransactionManagement //事务管理
@RequestMapping("/restfulapi")
public class RestfulController {
    @Autowired RestfulService restfulService;

    @RequestMapping(value = "/addBlogVisitor", method = RequestMethod.POST)
    public Result addBlogVisitor(BlogVisitor blogVisitor) {
        try {
            restfulService.addBlogVisitor(blogVisitor);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存访问者失败!");
        }
        return Result.ok("保存访问者成功!").put("data",blogVisitor);
    }

    @RequestMapping(value = "/deleteBlogVisitor",method = RequestMethod.DELETE)
    public Result deleteBlogVisitor(Long id) {
        try {
            restfulService.deleteBlogVisitor(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除访问者失败!");
        }
        return Result.ok("删除访问者成功!");
    }

    @RequestMapping(value = "/updateBlogVisitor",method = RequestMethod.POST)
    public Result updateBlogVisitor(BlogVisitor blogVisitor) {
        try {
            restfulService.updateBlogVisitor(blogVisitor);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新访问者失败!");
        }
        return Result.ok("更新访问者成功!");
    }

    @RequestMapping(value = "/qryBlogVisitorList",method = RequestMethod.GET)
    public Result qryBlogVisitorList() {
        List<BlogVisitor> resultList = null;
        try {
            resultList = restfulService.qryBlogVisitorList();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("查询访问者失败!");
        }
        if(resultList == null)
            return Result.error("查询不到数据!");
        return Result.ok("查询访问者成功!").put("data",resultList);
    }


//    @GetMapping("/user")
//    @Secured("ROLE_USER")
//    public Authentication getUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
//    }
//
//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }
}