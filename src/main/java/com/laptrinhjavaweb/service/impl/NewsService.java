package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewsDAO newDAO;

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newsModel) {
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newsModel.setCreatedBy("");
		Long newsId = newDAO.save(newsModel);
		return newDAO.findOne(newsId);
	}

	@Override
	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNew = newDAO.findOne(updateNews.getId());
		updateNews.setCreatedBy(oldNew.getCreatedBy());
		updateNews.setCreatedDate(oldNew.getCreatedDate());
		updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updateNews.setModifiedBy("");
		newDAO.update(updateNews);
		return newDAO.findOne(updateNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			// 1. delete comment
			// 2. delete news
			newDAO.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageable pageable) {
		return newDAO.findAll(pageable);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

}
