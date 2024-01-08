package com.laptrinhjavaweb.builder;

public class CategoriesBuilder {
    private String category;
    private String createdBy;

    public String getCategory() {
        return category;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    private CategoriesBuilder(Builder builder){
        this.category = builder.category;
        this.createdBy = builder.createdBy;
    }

    public static class Builder{
        private String category;
        private String createdBy;

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public CategoriesBuilder build(){
            return new CategoriesBuilder(this);
        }
    }
}
