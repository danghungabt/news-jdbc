package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.*;
import com.laptrinhjavaweb.model.response.recent.BlogsRecentResponseModel;

public interface IBlogsConverter {
    BlogsResponseModel convertToBlogsResponseModel(BlogsModel blogsModel);
    BlogsModel convertToBlogsModel(BlogsModel blogsModel);
    BlogWithCategoryResponseModel convertToBlogWithCategoryModel(
            BlogWithCategoryModel blogWithCategoryModel);
    MiniBlogsResponseModel convertToMiniBlogsResponseModel(BlogsModel blogsModel);
    MiniBlogsResponseModel convertToMiniBlogsResponseModel(BlogsResponseModel blogsResponseModel);
    MiniBlogWithCategoryResponseModel convertToMiniBlogWithCategoryModel(
            BlogWithCategoryModel blogWithCategoryModel);
    BlogsRecentResponseModel convertToBlogRecent(BlogsModel blogsModel);

}
