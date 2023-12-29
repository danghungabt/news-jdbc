package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.dao.IBlogsDAO;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBlogsService;
import com.laptrinhjavaweb.sort.Sorter;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlogsService implements IBlogsService {

    @Inject
    private IBlogsConverter blogsConverter;

    @Inject
    private IBlogsDAO blogsDAO;

    @Override
    public BlogsResponseModel insert(BlogsModel blogsModel) {
        blogsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        blogsModel.setCreatedBy("admin");
        BlogsModel newBlog = blogsConverter.convertToBlogsModel(blogsModel);
        Long id = blogsDAO.insert(newBlog);
        return blogsConverter.convertToBlogsResponseModel(blogsDAO.findOne(id));
    }

    @Override
    public List<BlogsResponseModel> insertAll(List<BlogsModel> blogsModels) {
        List<BlogsResponseModel> results = new ArrayList<>();
        for(BlogsModel item : blogsModels){
            item.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            item.setCreatedBy("admin");
            BlogsModel newBlog = blogsConverter.convertToBlogsModel(item);
            Long id = blogsDAO.insert(newBlog);
            results.add(blogsConverter.convertToBlogsResponseModel(blogsDAO.findOne(id)));
        }
        return results;
    }

    @Override
    public List<BlogsModel> findAll() {
        return blogsDAO.findAll();
    }

    @Override
    public List<BlogsResponseModel> findAllClient() {
        return blogsDAO.findAll().stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList());
    }

    @Override
    public BlogsModel findOne(Long id) {
        return blogsDAO.findOne(id);
    }

    @Override
    public BlogsResponseModel findOneClient(Long id) {
        return blogsConverter.convertToBlogsResponseModel(blogsDAO.findOne(id));
    }

    @Override
    public BlogsModel findOneBySlug(String slugBlog) {
        return blogsDAO.findOneBySlug(slugBlog);
    }

    @Override
    public BlogsResponseModel findOneBySlugClient(String slugBlog) {
        return blogsConverter.convertToBlogsResponseModel(blogsDAO.findOneBySlug(slugBlog));
    }

    @Override
    public List<BlogsModel> findByCategoryId(Long categoryId) {
        return blogsDAO.findByCategoryId(categoryId);
    }

    @Override
    public List<BlogsResponseModel> findByCategoryIdClient(Long categoryId) {
        return blogsDAO.findByCategoryId(categoryId).stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogsResponseModel> findByKeyClient(String key) {
        return blogsDAO.findByKey(key).stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList());
    }

    @Override
    public PagingModel<BlogsResponseModel> findAllClientWithPageable(Integer page) {

        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);

        PagingModel<BlogsResponseModel> result = new PagingModel<BlogsResponseModel>();

        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(blogsDAO.findAll().size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        result.setListResult(blogsDAO.findAllWithPageable(pageable).stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public PagingModel<BlogsResponseModel> findByCategoryIdClientWithPageable(Long categoryId, Integer page) {
        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);

        PagingModel<BlogsResponseModel> result = new PagingModel<BlogsResponseModel>();

        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(blogsDAO.findByCategoryId(categoryId).size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        result.setListResult(blogsDAO.findByCategoryIdWithPageable(categoryId, pageable).stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public PagingModel<BlogsResponseModel> findByKeyClientWithPageable(String key, Integer page) {
        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);

        PagingModel<BlogsResponseModel> result = new PagingModel<BlogsResponseModel>();

        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(blogsDAO.findByKey(key).size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        result.setListResult(blogsDAO.findByKeyWithPageable(key, pageable).stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<BlogsResponseModel> getRecent() {
        return blogsDAO.getRecent().stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .collect(Collectors.toList());
    }
}
