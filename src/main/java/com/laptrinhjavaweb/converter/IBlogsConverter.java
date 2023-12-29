package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;

public interface IBlogsConverter {
    BlogsResponseModel convertToBlogsResponseModel(BlogsModel blogsModel);
    BlogsModel convertToBlogsModel(BlogsModel blogsModel);
}
