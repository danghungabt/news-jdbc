package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;

public interface ICategoriesConverter {
    CategoriesResponseModel convertToCategoriesResponseModel(CategoriesModel categoriesModel);
    CategoriesModel convertToCategoriesModel(CategoriesModel categoriesModel);
}
