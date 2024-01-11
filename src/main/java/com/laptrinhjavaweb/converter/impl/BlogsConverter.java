package com.laptrinhjavaweb.converter.impl;

import com.laptrinhjavaweb.converter.IBlogsConverter;
import com.laptrinhjavaweb.dao.ICategoriesDAO;
import com.laptrinhjavaweb.dao.ICommentsDAO;
import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.*;
import com.laptrinhjavaweb.model.response.recent.BlogsRecentResponseModel;
import com.laptrinhjavaweb.utils.EncodeUtils;
import com.laptrinhjavaweb.utils.StringUtils;
import com.laptrinhjavaweb.utils.TimestampUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;

public class BlogsConverter implements IBlogsConverter {
    @Inject
    private ModelMapper modelMapper;

    @Inject
    private CategoriesConverter categoriesConverter;

    @Inject
    private ICommentsDAO commentsDAO;

    @Override
    public BlogsResponseModel convertToBlogsResponseModel(BlogsModel blogsModel) {
        BlogsResponseModel result = modelMapper.map(blogsModel, BlogsResponseModel.class);
//        result.setTitle(StringEscapeUtils.unescapeJava(blogsModel.getTitle()));
//        result.setContent(StringEscapeUtils.unescapeJava(blogsModel.getContent()));
        return result;
    }

    @Override
    public BlogsModel convertToBlogsModel(BlogsModel blogsModel) {
        BlogsModel result = blogsModel;

//        result.setTitle(EncodeUtils.encoding(blogsModel.getTitle()));
//        result.setContent(EncodeUtils.encoding(blogsModel.getContent()));

        return result;
    }

    @Override
    public BlogWithCategoryResponseModel convertToBlogWithCategoryModel(BlogWithCategoryModel blogWithCategoryModel) {
        BlogWithCategoryResponseModel response = new BlogWithCategoryResponseModel();

        response.setBlogsResponseModel(convertToBlogsResponseModel(blogWithCategoryModel.getBlogsModel()));
        response.setCategoriesResponseModel(categoriesConverter.convertToCategoriesResponseModel(
                                                                    blogWithCategoryModel.getCategoriesModel()));

        response.setTotalComment(commentsDAO.getTotalItemByBlogId(blogWithCategoryModel.getBlogsModel().getId()));

        return response;
    }

    @Override
    public MiniBlogsResponseModel convertToMiniBlogsResponseModel(BlogsModel blogsModel) {
        MiniBlogsResponseModel result = modelMapper.map(blogsModel, MiniBlogsResponseModel.class);
//        result.setTitle(StringEscapeUtils.unescapeJava(blogsModel.getTitle()));

        result.setContent(StringUtils.customSubstring(blogsModel.getContent(),
                100));
        return result;
    }

    @Override
    public MiniBlogsResponseModel convertToMiniBlogsResponseModel(BlogsResponseModel blogsResponseModel) {
        MiniBlogsResponseModel result = modelMapper.map(blogsResponseModel, MiniBlogsResponseModel.class);
//        result.setTitle(StringEscapeUtils.unescapeJava(blogsResponseModel.getTitle()));

        result.setContent(StringUtils.customSubstring(blogsResponseModel.getContent(),
                100));

        return result;
    }

    @Override
    public MiniBlogWithCategoryResponseModel convertToMiniBlogWithCategoryModel(BlogWithCategoryModel blogWithCategoryModel
                                                        , Integer countComment) {
        MiniBlogWithCategoryResponseModel response = new MiniBlogWithCategoryResponseModel();

        response.setBlogsResponseModel(convertToMiniBlogsResponseModel(blogWithCategoryModel.getBlogsModel()));
        response.setCategoriesResponseModel(categoriesConverter.convertToCategoriesResponseModel(
                blogWithCategoryModel.getCategoriesModel()));

        response.setTotalComment(countComment);

        return response;
    }

    @Override
    public BlogsRecentResponseModel convertToBlogRecent(BlogsModel blogsModel, CategoriesModel categoriesModel) {
        BlogsRecentResponseModel result = modelMapper.map(blogsModel, BlogsRecentResponseModel.class);
//        result.setTitle(StringEscapeUtils.unescapeJava(blogsModel.getTitle()));

        result.setSlugCategory(categoriesModel.getSlugCategory());
        return result;
    }

    @Override
    public BlogsResponseServerModel convertToBlogResponseServer(BlogsModel blogsModel) {
        BlogsResponseServerModel result = modelMapper.map(blogsModel, BlogsResponseServerModel.class);

        result.setCreatedDateStr(TimestampUtils.convertToString(blogsModel.getCreatedDate()));
        result.setModifiedDateStr(TimestampUtils.convertToString(blogsModel.getModifiedDate()));

        return result;
    }


}
