package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.CategoriesBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.request.CategoriesSearchRequestModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoriesService;
import com.laptrinhjavaweb.utils.TimestampUtils;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    public CategoriesResponseModel update(CategoriesModel categoriesModel) {
        CategoriesModel oldCategories = categoriesDAO.findOne(categoriesModel.getId());
        categoriesModel.setCreatedBy(oldCategories.getCreatedBy());
        categoriesModel.setCreatedDate(oldCategories.getCreatedDate());
//        categoriesModel.setModifiedDate(TimestampUtils.currentTimestampInHCM());
        categoriesModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        categoriesDAO.update(categoriesModel);
        return categoriesConverter.convertToCategoriesResponseModel(categoriesDAO.findOne(categoriesModel.getId()));
    }

    @Override
    public List<CategoriesModel> findAll(Pageable pageable) {
        return categoriesDAO.findAll(pageable);
    }

    @Override
    public List<CategoriesModel> findByCondition(Pageable pageable, CategoriesSearchRequestModel requestModel) {

        CategoriesBuilder builder = convertRequestModelToBuilder(requestModel);

        return categoriesDAO.findByCondition(pageable, builder);
    }

    private CategoriesBuilder convertRequestModelToBuilder(CategoriesSearchRequestModel requestModel) {
        CategoriesBuilder result = new CategoriesBuilder.Builder()
                .setCategory(requestModel.getCategory())
                .setCreatedBy(requestModel.getCreatedBy())
                .build();

        return result;
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

    @Override
    public int getTotalItem() {
        return categoriesDAO.getTotalItem();
    }

    @Override
    public int getTotalItemByCondition(CategoriesSearchRequestModel requestModel){

        CategoriesBuilder builder = convertRequestModelToBuilder(requestModel);

        return categoriesDAO.getTotalItemByCondition(builder);
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            categoriesDAO.delete(id);
        }
    }

    @Override
    public Map<Long, String> getMapCategories() {
        Map<Long, String> result = new LinkedHashMap<>();
        List<CategoriesModel> categoriesModels = categoriesDAO.findAll();
        for(CategoriesModel item: categoriesModels){
            result.put(item.getId(), item.getCategory());
        }
        return result;
    }

}
