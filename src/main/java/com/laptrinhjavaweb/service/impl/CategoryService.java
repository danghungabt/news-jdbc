package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryServeice;

public class CategoryService implements ICategoryServeice {

	/*private ICategoryDAO categoryDao;

	public CategoryService() {
		categoryDao = new CategoryDAO();
	}*/
	
	@Inject
	private ICategoryDAO categoryDao;

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

}
