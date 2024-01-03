package com.laptrinhjavaweb.model.response.recent;

import com.laptrinhjavaweb.model.response.AbtractResponseModel;

public class BlogsRecentResponseModel extends AbtractResponseModel {
    private String title;
    private String slugBlog;
    private Long categoryId;
    private String slugCategory;

    public String getSlugCategory() {
        return slugCategory;
    }

    public void setSlugCategory(String slugCategory) {
        this.slugCategory = slugCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlugBlog() {
        return slugBlog;
    }

    public void setSlugBlog(String slugBlog) {
        this.slugBlog = slugBlog;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
