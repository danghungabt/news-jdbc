package com.laptrinhjavaweb.builder;

public class BlogsBuilder {

    private String title;
    private Long categoriesId;
    private String createdBy;
    private String modifiedBy;

    public String getTitle() {
        return title;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    private BlogsBuilder(Builder builder){
        this.title = builder.title;
        this.categoriesId = builder.categoriesId;
        this.createdBy = builder.createdBy;
        this.modifiedBy = builder.modifiedBy;
    }

    public static class Builder{
        private String title;
        private Long categoriesId;
        private String createdBy;
        private String modifiedBy;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCategoriesId(Long categoriesId) {
            this.categoriesId = categoriesId;
            return this;
        }

        public Builder setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Builder setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
            return this;
        }

        public BlogsBuilder build(){
            return new BlogsBuilder(this);
        }
    }
}
