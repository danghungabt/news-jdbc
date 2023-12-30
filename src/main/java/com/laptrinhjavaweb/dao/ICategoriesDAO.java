package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface ICategoriesDAO extends GenericDAO<CategoriesModel> {
    Long insert(CategoriesModel categoriesModel);
    CategoriesModel findOne(Long id);
    List<CategoriesModel> findAll();
    CategoriesModel findOneBySlug(String slugCategory);
    List<CategoriesModel> findAllWithPageable(Pageable pageable);
}
