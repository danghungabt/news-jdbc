package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.request.CategoriesSearchRequestModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoriesService {
    CategoriesResponseModel insert(CategoriesModel categoriesModel);
    CategoriesResponseModel update(CategoriesModel categoriesModel);
//    Long insert(CategoriesModel categoriesModel);

    List<CategoriesModel> findAll(Pageable pageable);

    List<CategoriesModel> findByCondition(Pageable pageable, CategoriesSearchRequestModel requestModel);

    List<CategoriesResponseModel> findAllClient();

    CategoriesModel findOne(Long id);

    CategoriesResponseModel findOneClient(Long id);

    CategoriesModel findOneBySlug(String slugCategory);

    CategoriesResponseModel findOneBySlugClient(String slugCategory);

    PagingModel<CategoriesResponseModel> findAllClientWithPageable(Integer page);

    int getTotalItem();

    int getTotalItemByCondition(CategoriesSearchRequestModel requestModel);

    void delete(long[] ids);

    Map<Long, String> getMapCategories();

}
