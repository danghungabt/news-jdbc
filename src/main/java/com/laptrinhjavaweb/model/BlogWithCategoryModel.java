package com.laptrinhjavaweb.model;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;

public class BlogWithCategoryModel {

    private BlogsModel blogsModel;
    private CategoriesModel categoriesModel;

    public BlogsModel getBlogsModel() {
        return blogsModel;
    }

    public void setBlogsModel(BlogsModel blogsModel) {
        this.blogsModel = blogsModel;
    }

    public CategoriesModel getCategoriesModel() {
        return categoriesModel;
    }

    public void setCategoriesModel(CategoriesModel categoriesModel) {
        this.categoriesModel = categoriesModel;
    }
}
