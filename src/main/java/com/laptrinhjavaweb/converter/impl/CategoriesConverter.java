package com.laptrinhjavaweb.converter.impl;

import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.utils.EncodeUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class CategoriesConverter implements ICategoriesConverter {
    @Inject
    private ModelMapper modelMapper;

    @Override
    public CategoriesResponseModel convertToCategoriesResponseModel(CategoriesModel categoriesModel){
        CategoriesResponseModel result = modelMapper.map(categoriesModel, CategoriesResponseModel.class);
//        result.setCategory(StringEscapeUtils.unescapeJava(categoriesModel.getCategory()));
        return result;
    }

    @Override
    public CategoriesModel convertToCategoriesModel(CategoriesModel categoriesModel) {
        CategoriesModel result = categoriesModel;
//        result.setCategory(EncodeUtils.encoding(categoriesModel.getCategory()));
        return result;
    }
}
