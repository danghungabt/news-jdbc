package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.ICommentsConverter;
import com.laptrinhjavaweb.dao.IBlogsDAO;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.TreeNodeModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
import com.laptrinhjavaweb.model.response.recent.CommentsRecentResponseModel;
import com.laptrinhjavaweb.service.ICommentsService;
import com.laptrinhjavaweb.utils.CommentUtils;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class CommentsService implements ICommentsService {

    @Inject
    private ICommentsDAO commentsDAO;

    @Inject
    private ICommentsConverter commentsConverter;

    @Inject
    private ICategoriesDAO categoriesDAO;

    @Inject
    private IBlogsDAO blogsDAO;

    @Override
    public CommentsResponseModel insert(CommentsModel commentsModel) {
        commentsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        commentsModel.setCreatedBy("admin");
        CommentsModel newComment = commentsConverter.convertToCommentsModel(commentsModel);
        Long id = commentsDAO.insert(newComment);
        return commentsConverter.convertToCommentsResponseModel(commentsDAO.findOne(id));
    }

    @Override
    public List<CommentsModel> findAll() {
        return null;
    }

    @Override
    public List<CommentsResponseModel> findAllClient() {
        return null;
    }

    @Override
    public CommentsModel findOne(Long id) {
        return null;
    }

    @Override
    public CommentsResponseModel findOneClient(Long id) {
        return null;
    }

    @Override
    public List<TreeNodeModel<CommentsResponseModel>> findByBlogId(Long blogId) {

        List<CommentsResponseModel> commentsResponseModels = commentsDAO.findByBlogId(blogId).stream()
                                                            .map(item -> commentsConverter.convertToCommentsResponseModel(item))
                                                            .collect(Collectors.toList());

        return CommentUtils.buildTrees(commentsResponseModels);
    }

    @Override
    public List<CommentsRecentResponseModel> getRecent() {

        List<BlogsModel> blogsModels = blogsDAO.findAll();

        List<CategoriesModel> categoriesModels = categoriesDAO.findAll();

        return commentsDAO.getRecent().stream()
                .map(item -> {
                    for(BlogsModel blogsModel: blogsModels){
                        if(blogsModel.getId() == item.getBlogId()){
                            for(CategoriesModel categoriesModel: categoriesModels){
                                if(categoriesModel.getId() == blogsModel.getCategoryId()){
                                    return commentsConverter.convertToCommentRecent(item, blogsModel, categoriesModel);
                                }
                            }
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
