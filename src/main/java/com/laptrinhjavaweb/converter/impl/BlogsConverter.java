package com.laptrinhjavaweb.converter.impl;

import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.utils.EncodeUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;

public class BlogsConverter implements IBlogsConverter {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public BlogsResponseModel convertToBlogsResponseModel(BlogsModel blogsModel) {
        BlogsResponseModel result = modelMapper.map(blogsModel, BlogsResponseModel.class);
        result.setTitle(StringEscapeUtils.unescapeJava(blogsModel.getTitle()));
        result.setContent(StringEscapeUtils.unescapeJava(blogsModel.getContent()));
        return result;
    }

    @Override
    public BlogsModel convertToBlogsModel(BlogsModel blogsModel) {
        BlogsModel result = blogsModel;

        result.setTitle(EncodeUtils.encoding(blogsModel.getTitle()));
        result.setContent(EncodeUtils.encoding(blogsModel.getContent()));

        return result;
    }
}
