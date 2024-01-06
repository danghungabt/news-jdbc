package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.CountCommentModel;

import java.util.List;

public interface ICommentsDAO extends GenericDAO<CommentsModel> {
    Long insert(CommentsModel commentsModel);
    CommentsModel findOne(Long id);
    List<CommentsModel> findAll();
    List<CommentsModel> findByBlogId(Long blogId);
    List<CommentsModel> getRecent();
    Integer getTotalItemByBlogId(Long id);
    List<CountCommentModel> getCountComment();
}
