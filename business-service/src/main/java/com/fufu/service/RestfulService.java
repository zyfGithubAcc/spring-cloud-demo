package com.fufu.service;

import com.fufu.mapper.BlogVisitorMapper;
import com.fufu.entity.BlogVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestfulService {
    @Autowired private BlogVisitorMapper blogVisitorMapper;

    public void addBlogVisitor(BlogVisitor blogVisitor)throws Exception {
        blogVisitorMapper.addBlogVisitor(blogVisitor);
        throw new RuntimeException("heiheihei");//事务测试
    }

    public void deleteBlogVisitor(Long id) {
        blogVisitorMapper.deleteBlogVisitor(id);
    }

    public void updateBlogVisitor(BlogVisitor blogVisitor) {
        blogVisitorMapper.updateBlogVisitor(blogVisitor);
    }

    public List<BlogVisitor> qryBlogVisitorList() {
        return blogVisitorMapper.qryBlogVisitorList();
    }

    public BlogVisitorMapper getBlogVisitorMapper() {
        return blogVisitorMapper;
    }

    public void setBlogVisitorMapper(BlogVisitorMapper blogVisitorMapper) {
        this.blogVisitorMapper = blogVisitorMapper;
    }
}