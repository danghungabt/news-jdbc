package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.BlogWithCategoryModel;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogWithCategoryMapper implements RowMapper<BlogWithCategoryModel> {


	@Override
	public BlogWithCategoryModel mapRow(ResultSet resultSet) {
		try {


			BlogWithCategoryModel blogWithCategoryModel = new BlogWithCategoryModel();

			BlogsModel blog = new BlogsModel();
			blog.setId(resultSet.getLong("b.id"));
			blog.setTitle(resultSet.getString("b.title"));
			blog.setSlugBlog(resultSet.getString("b.slugblog"));
			blog.setCategoryId(resultSet.getLong("b.categoryid"));
			blog.setDateSubmitted(resultSet.getString("b.datesubmitted"));
			blog.setContent(resultSet.getString("b.content"));
			blog.setCreatedBy(resultSet.getString("b.createdby"));
			blog.setCreatedDate(resultSet.getTimestamp("b.createddate"));
			blog.setModifiedDate(resultSet.getTimestamp("b.modifieddate"));
			blog.setModifiedBy(resultSet.getString("b.modifiedby"));

			CategoriesModel category = new CategoriesModel();
			category.setId(resultSet.getLong("c.id"));
			category.setCategory(resultSet.getString("c.category"));
			category.setSlugCategory(resultSet.getString("c.slugcategory"));

			blogWithCategoryModel.setBlogsModel(blog);
			blogWithCategoryModel.setCategoriesModel(category);

			return blogWithCategoryModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
