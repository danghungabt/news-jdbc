package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;

import java.util.List;

public interface ICategoriesService {
    CategoriesResponseModel insert(CategoriesModel categoriesModel);
    List<CategoriesModel> findAll();
    List<CategoriesResponseModel> findAllClient();
    CategoriesModel findOne(Long id);
    CategoriesResponseModel findOneClient(Long id);
    CategoriesModel findOneBySlug(String slugCategory);
    CategoriesResponseModel findOneBySlugClient(String slugCategory);
    PagingModel<CategoriesResponseModel> findAllClientWithPageable(Integer page);
}
