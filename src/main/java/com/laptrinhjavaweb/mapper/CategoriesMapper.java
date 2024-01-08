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
			category.setCreatedBy(resultSet.getString("createdby"));
			category.setCreatedDate(resultSet.getTimestamp("createddate"));
			category.setModifiedBy(resultSet.getString("modifieddate"));
			category.setModifiedBy(resultSet.getString("modifiedby"));
			return category;
		} catch (SQLException e) {
			return null;
		}
	}
}
