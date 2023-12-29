package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.ICommentsConverter;
import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.TreeNodeModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
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
    public List<CommentsResponseModel> getRecent() {
        return commentsDAO.getRecent().stream()
                .map(item -> commentsConverter.convertToCommentsResponseModel(item))
                .collect(Collectors.toList());
    }
}
