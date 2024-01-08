package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.builder.CategoriesBuilder;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.paging.Pageable;

import java.util.List;

public interface ICategoriesDAO extends GenericDAO<CategoriesModel> {
    Long insert(CategoriesModel categoriesModel);
    void update(CategoriesModel categoriesModel);
    CategoriesModel findOne(Long id);
    List<CategoriesModel> findAll(Pageable pageable);
    List<CategoriesModel> findAll();
    CategoriesModel findOneBySlug(String slugCategory);
    List<CategoriesModel> findAllWithPageable(Pageable pageable);
    int getTotalItem();
    List<CategoriesModel> findByCondition(Pageable pageable, CategoriesBuilder categoriesBuilder);
    int getTotalItemByCondition(CategoriesBuilder categoriesBuilder);
    void delete(long id);
}
