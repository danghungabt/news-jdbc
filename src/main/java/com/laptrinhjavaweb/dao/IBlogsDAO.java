package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface IBlogsDAO extends GenericDAO<BlogsModel> {
    Long insert(BlogsModel blogsModel);
    BlogsModel findOne(Long id);
    List<BlogsModel> findAll();
    BlogsModel findOneBySlug(String slugBlog);
    List<BlogsModel> findByCategoryId(Long categoryId);
    List<BlogsModel> findByKey(String key);
    List<BlogsModel> findAllWithPageable(Pageable pageable);
    List<BlogsModel> findByCategoryIdWithPageable(Long categoryId, Pageable pageable);
    List<BlogsModel> findByKeyWithPageable(String key, Pageable pageable);
    List<BlogsModel> getRecent();
}
