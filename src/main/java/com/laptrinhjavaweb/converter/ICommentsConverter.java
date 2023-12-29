package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;

public interface ICommentsConverter {
    CommentsResponseModel convertToCommentsResponseModel(CommentsModel commentsModel);
    CommentsModel convertToCommentsModel(CommentsModel commentsModel);
}
