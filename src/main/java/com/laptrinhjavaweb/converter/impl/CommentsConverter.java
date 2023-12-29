package com.laptrinhjavaweb.converter.impl;

import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.converter.ICommentsConverter;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
import com.laptrinhjavaweb.utils.EncodeUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class CommentsConverter implements ICommentsConverter {
    @Inject
    private ModelMapper modelMapper;

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
}
