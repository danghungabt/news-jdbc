package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.BlogWithCategoryResponseModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.model.response.MiniBlogWithCategoryResponseModel;
import com.laptrinhjavaweb.model.response.recent.BlogsRecentResponseModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface IBlogsService {
    BlogsResponseModel insert(BlogsModel blogsModel);
    List<BlogsResponseModel> insertAll(List<BlogsModel> blogsModels);
    List<BlogsModel> findAll();
    List<BlogsResponseModel> findAllClient();
    BlogsModel findOne(Long id);
    BlogsResponseModel findOneClient(Long id);
    BlogsModel findOneBySlug(String slugBlog);
    BlogsResponseModel findOneBySlugClient(String slugBlog);
    List<BlogsModel> findByCategoryId(Long categoryId);
    List<BlogsResponseModel> findByCategoryIdClient(Long categoryId);
    List<BlogsResponseModel> findByKeyClient(String key);
    PagingModel<BlogsResponseModel> findAllClientWithPageable(Integer page);
    PagingModel<BlogsResponseModel> findByCategoryIdClientWithPageable(Long categoryId, Integer page);
    PagingModel<BlogsResponseModel> findByKeyClientWithPageable(String key, Integer page);
    List<BlogsRecentResponseModel> getRecent();

    BlogWithCategoryResponseModel findOneBySlugClientPlus(String slugBlog);
    BlogWithCategoryResponseModel findOneClientPlus(Long id);
    PagingModel<MiniBlogWithCategoryResponseModel> findAllClientWithPageablePlus(Integer page);
    PagingModel<MiniBlogWithCategoryResponseModel> findByCategoryIdClientWithPageablePlus(Long categoryId, Integer page);
    PagingModel<MiniBlogWithCategoryResponseModel> findByKeyClientWithPageablePlus(String key, Integer page);
}
