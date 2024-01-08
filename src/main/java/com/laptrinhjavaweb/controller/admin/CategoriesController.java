package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.request.CategoriesSearchRequestModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICategoriesService;
import com.laptrinhjavaweb.service.ICategoryServeice;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtils;
import com.laptrinhjavaweb.utils.MessageUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/admin-categories" })
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private INewsService newsService;

	@Inject
	private ICategoriesService categoriesService;

	@Inject
	private ICategoryServeice categoryServeice;

	@Inject
	private IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoriesSearchRequestModel requestModel = FormUtils.toModel(
				CategoriesSearchRequestModel.class, request);
		String view ="";
		CategoriesModel model = new CategoriesModel();
		if(requestModel.getType().equals(SystemConstant.LIST)){
			Pageable pageable = new PageRequest(requestModel.getPage(), requestModel.getMaxPageItem(),
					new Sorter(requestModel.getSortName(), requestModel.getSortBy()));
			model.setListResult(categoriesService.findByCondition(pageable, requestModel));
			model.setPage(pageable.getPage());
			model.setMaxPageItem(pageable.getLimit());
			model.setSortBy(pageable.getSorter().getSortBy());
			model.setSortName(pageable.getSorter().getSortName());
			model.setTotalItem(categoriesService.getTotalItemByCondition(requestModel));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			request.setAttribute(SystemConstant.MODEL_SEARCH, requestModel);
			request.setAttribute(SystemConstant.USERS_MAP, userService.findByStatus(1));
			view = "/views/admin/categories/list.jsp";
		} else if(requestModel.getType().equals(SystemConstant.EDIT)){
			if(requestModel.getId() != null){
				model = categoriesService.findOne(requestModel.getId());
			}
			request.setAttribute("categories", categoryServeice.findAll());
			view = "/views/admin/categories/edit.jsp";
		}
		MessageUtils.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
