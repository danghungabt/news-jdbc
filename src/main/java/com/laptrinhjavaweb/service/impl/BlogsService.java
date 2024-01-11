package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BlogsBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.converter.ICommentsConverter;
import com.laptrinhjavaweb.dao.IBlogsDAO;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.model.*;
import com.laptrinhjavaweb.model.request.BlogsSearchRequestModel;
import com.laptrinhjavaweb.model.response.*;
import com.laptrinhjavaweb.model.response.recent.BlogsRecentResponseModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBlogsService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.EncodeUtils;
import com.laptrinhjavaweb.utils.TimestampUtils;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlogsService implements IBlogsService {

    @Inject
    private IBlogsConverter blogsConverter;

    @Inject
    private ICategoriesConverter categoriesConverter;

    @Inject
    private IBlogsDAO blogsDAO;

    @Inject
    private ICategoriesDAO categoriesDAO;

    @Inject
    private ICommentsDAO commentsDAO;

    @Override
    public BlogsResponseModel insert(BlogsModel blogsModel) {
        blogsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        blogsModel.setDateSubmitted(TimestampUtils.convertToString(new Timestamp(System.currentTimeMillis())));
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
    public BlogsResponseModel update(BlogsModel blogsModel) {
        blogsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        blogsDAO.update(blogsModel);
        return blogsConverter.convertToBlogsResponseModel(blogsDAO.findOne(blogsModel.getId()));
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
        PagingModel<BlogsResponseModel> result = new PagingModel<BlogsResponseModel>();
        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);

        List<BlogsResponseModel> allBlogs = blogsDAO.findAll().stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .filter(item -> item.getTitle().contains(key) || item.getContent().contains(key))
                .collect(Collectors.toList());


        result.setTotalItem(allBlogs.size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        int inclusive = (page - 1) * SystemConstant.PAGE_SIZE;
        int exclusive = allBlogs.size() > SystemConstant.PAGE_SIZE ?
                            inclusive + SystemConstant.PAGE_SIZE
                            : inclusive + allBlogs.size();

        result.setListResult(IntStream.range(inclusive, exclusive)
                                .mapToObj(index -> allBlogs.get(index))
                                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<BlogsRecentResponseModel> getRecent() {


        List<CategoriesModel> categoriesModels = categoriesDAO.findAll();

        return blogsDAO.getRecent().stream()
                .map(item -> {
                    for(CategoriesModel categoriesModel: categoriesModels){
                        if(categoriesModel.getId() == item.getCategoryId()) {
                            return blogsConverter.convertToBlogRecent(item, categoriesModel);
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BlogWithCategoryResponseModel findOneBySlugClientPlus(String slugBlog) {

        BlogWithCategoryResponseModel result = new BlogWithCategoryResponseModel();

        BlogsResponseModel blogsResponseModel =
                blogsConverter.convertToBlogsResponseModel(blogsDAO.findOneBySlug(slugBlog));

        CategoriesResponseModel categoriesResponseModel =
                categoriesConverter.convertToCategoriesResponseModel(
                        categoriesDAO.findOne(blogsResponseModel.getCategoryId()));

        result.setTotalComment(commentsDAO.getTotalItemByBlogId(blogsResponseModel.getId()));

        result.setBlogsResponseModel(blogsResponseModel);
        result.setCategoriesResponseModel(categoriesResponseModel);

        return result;
    }

    @Override
    public BlogWithCategoryResponseModel findOneClientPlus(Long id) {

        BlogWithCategoryResponseModel result = new BlogWithCategoryResponseModel();

        BlogsResponseModel blogsResponseModel =
                blogsConverter.convertToBlogsResponseModel(blogsDAO.findOne(id));

        CategoriesResponseModel categoriesResponseModel =
                categoriesConverter.convertToCategoriesResponseModel(
                        categoriesDAO.findOne(blogsResponseModel.getCategoryId()));

        result.setTotalComment(commentsDAO.getTotalItemByBlogId(blogsResponseModel.getId()));

        result.setBlogsResponseModel(blogsResponseModel);
        result.setCategoriesResponseModel(categoriesResponseModel);

        return result;
    }

    @Override
    public PagingModel<MiniBlogWithCategoryResponseModel> findAllClientWithPageablePlus(Integer page) {
        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);
        PagingModel<MiniBlogWithCategoryResponseModel> result = new PagingModel<MiniBlogWithCategoryResponseModel>();
        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(blogsDAO.getTotalItem());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        List<CountCommentModel> countCommentModels = commentsDAO.getCountComment();

        result.setListResult(blogsDAO.findAllWithPageablePlus(pageable).stream()
                .map(item -> {
                    for (CountCommentModel countCommentModel : countCommentModels){
                        if(countCommentModel.getBlogId() == item.getBlogsModel().getId()){
                            return blogsConverter.convertToMiniBlogWithCategoryModel(item,
                                    countCommentModel.getCount());
                        }
                    }
                    return blogsConverter.convertToMiniBlogWithCategoryModel(item,
                            0);
                })
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public PagingModel<MiniBlogWithCategoryResponseModel> findByCategoryIdClientWithPageablePlus(Long categoryId, Integer page) {
        Pageable pageable = new PageRequest(page, SystemConstant.PAGE_SIZE);
        PagingModel<MiniBlogWithCategoryResponseModel> result = new PagingModel<MiniBlogWithCategoryResponseModel>();
        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);
        result.setTotalItem(blogsDAO.getTotalItemByCategoryId(categoryId));
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        List<CountCommentModel> countCommentModels = commentsDAO.getCountComment();

        result.setListResult(blogsDAO.findByCategoryIdWithPageablePlus(categoryId, pageable).stream()
                .map(item -> {
                    for (CountCommentModel countCommentModel : countCommentModels){
                        if(countCommentModel.getBlogId() == item.getBlogsModel().getId()){
                            return blogsConverter.convertToMiniBlogWithCategoryModel(item,
                                    countCommentModel.getCount());
                        }
                    }
                    return blogsConverter.convertToMiniBlogWithCategoryModel(item,
                            0);
                })
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public PagingModel<MiniBlogWithCategoryResponseModel> findByKeyClientWithPageablePlus(String key, Integer page) {
        List<CountCommentModel> countCommentModels = commentsDAO.getCountComment();

        PagingModel<MiniBlogWithCategoryResponseModel> result = new PagingModel<MiniBlogWithCategoryResponseModel>();
        result.setPage(page);
        result.setPageSize(SystemConstant.PAGE_SIZE);

        List<BlogsResponseModel> allBlogs = blogsDAO.findAll().stream()
                .map(item -> blogsConverter.convertToBlogsResponseModel(item))
                .filter(item -> item.getTitle().contains(key) || item.getContent().contains(key))
                .collect(Collectors.toList());


        result.setTotalItem(allBlogs.size());
        result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getPageSize()));

        int inclusive = (page - 1) * SystemConstant.PAGE_SIZE;
        int exclusive = allBlogs.size() > SystemConstant.PAGE_SIZE ?
                inclusive + SystemConstant.PAGE_SIZE
                : inclusive + allBlogs.size();

        List<MiniBlogWithCategoryResponseModel> blogWithCategoryResponseModels = new ArrayList<>();

        for(BlogsResponseModel item: allBlogs){
            MiniBlogWithCategoryResponseModel blogWithCategoryResponseModel = new MiniBlogWithCategoryResponseModel();
            blogWithCategoryResponseModel.setBlogsResponseModel(
                    blogsConverter.convertToMiniBlogsResponseModel(item));
            blogWithCategoryResponseModel.setCategoriesResponseModel(
                    categoriesConverter.convertToCategoriesResponseModel(
                            categoriesDAO.findOne(item.getCategoryId()))
            );

            for(CountCommentModel countCommentModel: countCommentModels){
                if(countCommentModel.getBlogId() == item.getId()){
                    blogWithCategoryResponseModel.setTotalComment(countCommentModel.getCount());
                }
            }

            if(blogWithCategoryResponseModel.getTotalComment() == null){
                blogWithCategoryResponseModel.setTotalComment(0);
            }

            blogWithCategoryResponseModels.add(blogWithCategoryResponseModel);
        }

        result.setListResult(IntStream.range(inclusive, exclusive)
                .mapToObj(index -> blogWithCategoryResponseModels.get(index))
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<BlogsResponseServerModel> findByCondition(Pageable pageable, BlogsSearchRequestModel blogsSearchRequestModel) {

        BlogsBuilder builder = convertToBlogBuilder(blogsSearchRequestModel);

        return blogsDAO.findByCondition(pageable, builder).stream()
                .map(item -> blogsConverter.convertToBlogResponseServer(item))
                .collect(Collectors.toList());
    }

    private BlogsBuilder convertToBlogBuilder(BlogsSearchRequestModel blogsSearchRequestModel) {
        BlogsBuilder result = new BlogsBuilder.Builder()
                .setTitle(blogsSearchRequestModel.getTitle())
                .setCategoriesId(blogsSearchRequestModel.getCategoriesId())
                .setCreatedBy(blogsSearchRequestModel.getCreatedBy())
                .setModifiedBy(blogsSearchRequestModel.getModifiedBy())
                .build();
        return result;
    }

    @Override
    public int getTotalItemByCondition(BlogsSearchRequestModel blogsSearchRequestModel) {
        BlogsBuilder builder = convertToBlogBuilder(blogsSearchRequestModel);

        return blogsDAO.getTotalItemByCondition(builder);
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            blogsDAO.delete(id);
        }
    }
}
