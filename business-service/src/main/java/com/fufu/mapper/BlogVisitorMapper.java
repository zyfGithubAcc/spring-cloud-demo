package com.fufu.mapper;

import com.fufu.entity.BlogVisitor;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BlogVisitorMapper {
    @Insert("insert into blog_visitor(visitor_name, visitor_gender, visitor_reason, visit_time) values(#{visitorName}, #{visitorGender}, #{visitorReason}, #{visitTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addBlogVisitor(BlogVisitor blogVisitor);

    @Delete("delete from blog_visitor where id = #{id}")
    void deleteBlogVisitor(@Param("id") Long id);

    @Update("update blog_visitor set visitor_name = #{visitorName}, visitor_gender = #{visitorGender}, visitor_reason = #{visitorReason}, visit_time = #{visitTime} where id = #{id}")
    void updateBlogVisitor(BlogVisitor blogVisitor);

    @Select("select id, visitor_name as visitorName, visitor_gender as visitorGender, visitor_reason as visitorReason, visit_time as visitTime from blog_visitor")
    List<BlogVisitor> qryBlogVisitorList();
}