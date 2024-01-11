package com.laptrinhjavaweb.model.response;

import com.laptrinhjavaweb.model.AbstractModel;

public class BlogsResponseServerModel extends AbstractModel<BlogsResponseServerModel> {
    private String title;
    private String slugBlog;
    private String createdBy;
    private String modifiedBy;
    private String createdDateStr;
    private String modifiedDateStr;

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

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCreatedDateStr() {
        return createdDateStr;
    }

    public void setCreatedDateStr(String createdDateStr) {
        this.createdDateStr = createdDateStr;
    }

    public String getModifiedDateStr() {
        return modifiedDateStr;
    }

    public void setModifiedDateStr(String modifiedDateStr) {
        this.modifiedDateStr = modifiedDateStr;
    }
}
