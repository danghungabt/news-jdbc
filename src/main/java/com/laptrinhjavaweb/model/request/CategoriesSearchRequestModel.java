package com.laptrinhjavaweb.model.request;

import com.laptrinhjavaweb.model.AbstractModel;

public class CategoriesSearchRequestModel extends AbstractModel<CategoriesSearchRequestModel> {

    private String category;
    private String createdBy;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
