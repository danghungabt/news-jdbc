package com.laptrinhjavaweb.model;

public class CountCommentModel {
    private Long blogId;
    private Integer count;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
