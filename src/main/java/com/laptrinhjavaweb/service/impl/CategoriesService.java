package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.service.ICategoriesService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriesService implements ICategoriesService {

    @Inject
    private ICategoriesDAO categoriesDAO;

    @Inject
    private ICategoriesConverter categoriesConverter;

    @Override
    public CategoriesResponseModel insert(CategoriesModel categoriesModel) {
        categoriesModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        CategoriesModel newCategoriesModel = categoriesConverter.convertToCategoriesModel(categoriesModel);
        Long id = categoriesDAO.insert(newCategoriesModel);
        return categoriesConverter.convertToCategoriesResponseModel(categoriesDAO.findOne(id));
    }

    @Override
    public List<CategoriesModel> findAll() {
        return categoriesDAO.findAll();
    }

    @Override
    public List<CategoriesResponseModel> findAllClient() {
        List<CategoriesResponseModel> results = categoriesDAO.findAll().stream()
                .map(item -> categoriesConverter.convertToCategoriesResponseModel(item))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    public CategoriesModel findOne(Long id) {
        return categoriesDAO.findOne(id);
    }

    @Override
    public CategoriesResponseModel findOneClient(Long id) {
        return categoriesConverter.convertToCategoriesResponseModel(categoriesDAO.findOne(id));
    }

    @Override
    public CategoriesModel findOneBySlug(String slugCategory) {
        return categoriesDAO.findOneBySlug(slugCategory);
    }

    @Override
    public CategoriesResponseModel findOneBySlugClient(String slugCategory) {
        return categoriesConverter.convertToCategoriesResponseModel(categoriesDAO.findOneBySlug(slugCategory));
    }
}
