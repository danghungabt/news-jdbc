package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogsMapper implements RowMapper<BlogsModel> {

	@Override
	public BlogsModel mapRow(ResultSet resultSet) {
		try {
			BlogsModel blog = new BlogsModel();
			blog.setId(resultSet.getLong("id"));
			blog.setTitle(resultSet.getString("title"));
			blog.setSlugBlog(resultSet.getString("slugblog"));
			blog.setCategoryId(resultSet.getLong("categoryid"));
			blog.setDateSubmitted(resultSet.getString("datesubmitted"));
			blog.setContent(resultSet.getString("content"));
			return blog;
		} catch (SQLException e) {
			return null;
		}
	}
}
