package com.laptrinhjavaweb.model;

public class CategoriesModel extends AbstractModel<CategoriesModel> {

    private String category;
    private String slugCategory;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSlugCategory() {
        return slugCategory;
    }

    public void setSlugCategory(String slugCategory) {
        this.slugCategory = slugCategory;
    }
}
