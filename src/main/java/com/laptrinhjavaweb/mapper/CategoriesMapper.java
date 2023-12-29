package com.laptrinhjavaweb.mapper;

import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesMapper implements RowMapper<CategoriesModel> {

	@Override
	public CategoriesModel mapRow(ResultSet resultSet) {
		try {
			CategoriesModel category = new CategoriesModel();
			category.setId(resultSet.getLong("id"));
			category.setCategory(resultSet.getString("category"));
			category.setSlugCategory(resultSet.getString("slugcategory"));
			return category;
		} catch (SQLException e) {
			return null;
		}
	}
}
