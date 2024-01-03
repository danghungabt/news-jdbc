package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.TreeNodeModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
import com.laptrinhjavaweb.model.response.recent.CommentsRecentResponseModel;

import java.util.List;

public interface ICommentsService {
    CommentsResponseModel insert(CommentsModel commentsModel);
    List<CommentsModel> findAll();
    List<CommentsResponseModel> findAllClient();
    CommentsModel findOne(Long id);
    CommentsResponseModel findOneClient(Long id);
    List<TreeNodeModel<CommentsResponseModel>> findByBlogId(Long blogId);
    List<CommentsRecentResponseModel> getRecent();
}
