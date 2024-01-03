package com.laptrinhjavaweb.model.response.recent;

import com.laptrinhjavaweb.model.response.AbtractResponseModel;

import java.sql.Timestamp;

public class CommentsRecentResponseModel extends AbtractResponseModel {
    private String name;
    private BlogsRecentResponseModel blog;

    public BlogsRecentResponseModel getBlog() {
        return blog;
    }

    public void setBlog(BlogsRecentResponseModel blog) {
        this.blog = blog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
