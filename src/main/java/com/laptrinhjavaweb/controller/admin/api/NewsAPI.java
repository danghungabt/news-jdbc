package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtils;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {
	@Inject
	private INewsService newsService;

	private static final long serialVersionUID = 5644404405623586930L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, SystemConstant.USER_KEY_SESSION)).getUserName());
		newsModel = newsService.save(newsModel);
		System.out.print(newsModel);
		mapper.writeValue(response.getOutputStream(), newsModel);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewsModel updateNews = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		updateNews.setModifiedBy(((UserModel) SessionUtils.getInstance().getValue(request, SystemConstant.USER_KEY_SESSION)).getUserName());
		updateNews = newsService.update(updateNews);
		mapper.writeValue(response.getOutputStream(), updateNews);

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newsService.delete(newsModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}

}
