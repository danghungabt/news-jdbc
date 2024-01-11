package com.laptrinhjavaweb.model.request;

import com.laptrinhjavaweb.model.AbstractModel;

public class BlogsSearchRequestModel extends AbstractModel<BlogsSearchRequestModel> {

    private String title;
    private Long categoriesId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }
}
