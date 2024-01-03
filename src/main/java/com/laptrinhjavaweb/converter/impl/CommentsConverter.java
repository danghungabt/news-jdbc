package com.laptrinhjavaweb.converter.impl;

import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.converter.ICommentsConverter;
import com.laptrinhjavaweb.dao.IBlogsDAO;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
import com.laptrinhjavaweb.model.response.recent.CommentsRecentResponseModel;
import com.laptrinhjavaweb.utils.EncodeUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class CommentsConverter implements ICommentsConverter {
    @Inject
    private ModelMapper modelMapper;

    @Inject
    private BlogsConverter blogsConverter;

    @Inject
    private IBlogsDAO blogsDAO;

    @Override
    public CommentsResponseModel convertToCommentsResponseModel(CommentsModel commentsModel) {
        CommentsResponseModel result = modelMapper.map(commentsModel, CommentsResponseModel.class);
        if(result.getParentId() == 0){
            result.setParentId(null);
        }
        result.setName(StringEscapeUtils.unescapeJava(commentsModel.getName()));
        result.setContent(StringEscapeUtils.unescapeJava(commentsModel.getContent()));
        return result;
    }

    @Override
    public CommentsModel convertToCommentsModel(CommentsModel commentsModel) {
        CommentsModel result = commentsModel;
        result.setName(EncodeUtils.encoding(commentsModel.getName()));
        result.setContent(EncodeUtils.encoding(commentsModel.getContent()));
        return result;
    }

    @Override
    public CommentsRecentResponseModel convertToCommentRecent(CommentsModel commentsModel) {
        CommentsRecentResponseModel result = modelMapper.map(
                commentsModel, CommentsRecentResponseModel.class);
        result.setName(StringEscapeUtils.unescapeJava(commentsModel.getName()));
        BlogsModel blogsModel = blogsDAO.findOne(commentsModel.getBlogId());

        result.setBlog(blogsConverter.convertToBlogRecent(blogsModel));

        return result;
    }
}
