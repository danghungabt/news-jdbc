package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.builder.BlogsBuilder;
import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface IBlogsDAO extends GenericDAO<BlogsModel> {
    Long insert(BlogsModel blogsModel);
    void update(BlogsModel blogsModel);
    BlogsModel findOne(Long id);
    List<BlogsModel> findAll();
    BlogsModel findOneBySlug(String slugBlog);
    List<BlogsModel> findByCategoryId(Long categoryId);
    List<BlogsModel> findByKey(String key);
    List<BlogsModel> findAllWithPageable(Pageable pageable);
    List<BlogsModel> findByCategoryIdWithPageable(Long categoryId, Pageable pageable);
    List<BlogsModel> findByKeyWithPageable(String key, Pageable pageable);
    List<BlogsModel> getRecent();

    List<BlogWithCategoryModel> findAllWithPageablePlus(Pageable pageable);
    List<BlogWithCategoryModel> findByCategoryIdWithPageablePlus(Long categoryId, Pageable pageable);

    int getTotalItem();
    int getTotalItemByCategoryId(Long categoryId);

    List<BlogsModel> findByCondition(Pageable pageable, BlogsBuilder builder);
    int getTotalItemByCondition(BlogsBuilder builder);
    void delete(long id);

}
