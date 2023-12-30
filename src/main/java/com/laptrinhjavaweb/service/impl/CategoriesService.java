package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
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

    @Override
    public PagingModel<CategoriesResponseModel> findAllClientWithPageable(Integer page) {
        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);

        PagingModel<CategoriesResponseModel> result = new PagingModel<CategoriesResponseModel>();

        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(categoriesDAO.findAll().size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        result.setListResult(categoriesDAO.findAllWithPageable(pageable).stream()
                .map(item -> categoriesConverter.convertToCategoriesResponseModel(item))
                .collect(Collectors.toList()));

        return result;
    }
}
