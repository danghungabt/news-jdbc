package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.request.BlogsSearchRequestModel;
import com.laptrinhjavaweb.model.request.CategoriesSearchRequestModel;
import com.laptrinhjavaweb.model.response.BlogsResponseServerModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.*;
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

@WebServlet(urlPatterns = { "/admin-blogs" })
public class BlogsController extends HttpServlet {
	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private INewsService newsService;

	@Inject
	private IBlogsService blogsService;

	@Inject
	private ICategoryServeice categoryServeice;

	@Inject
	private ICategoriesService categoriesService;

	@Inject
	private IUserService userService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String view ="";
		if(type.equals(SystemConstant.LIST)){
			BlogsSearchRequestModel requestModel = FormUtils.toModel(
					BlogsSearchRequestModel.class, request);
			Pageable pageable = new PageRequest(requestModel.getPage(), requestModel.getMaxPageItem(),
					new Sorter(requestModel.getSortName(), requestModel.getSortBy()));
			BlogsResponseServerModel model = new BlogsResponseServerModel();
//			model.setListResult(blogsService.findByCondition(pageable, requestModel));
			model.setListResult(blogsService.findByCondition(pageable, requestModel));
			model.setPage(pageable.getPage());
			model.setMaxPageItem(pageable.getLimit());
			model.setSortBy(pageable.getSorter().getSortBy());
			model.setSortName(pageable.getSorter().getSortName());
//			model.setTotalItem(blogsService.getTotalItemByCondition(requestModel));
			model.setTotalItem(blogsService.getTotalItemByCondition(requestModel));
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
			request.setAttribute(SystemConstant.USERS_MAP, userService.findByStatus(1));
			request.setAttribute(SystemConstant.MODEL, model);
			request.setAttribute(SystemConstant.MODEL_SEARCH, requestModel);
			view = "/views/admin/blogs/list.jsp";
		} else if(type.equals(SystemConstant.EDIT)){
			BlogsModel model = FormUtils.toModel(
					BlogsModel.class, request);
			if(model.getId() != null){
				model = blogsService.findOne(model.getId());
			}
            request.setAttribute(SystemConstant.MODEL, model);
			view = "/views/admin/blogs/edit.jsp";
		}
        request.setAttribute(SystemConstant.CATEGORIES_MAP, categoriesService.getMapCategories());
		MessageUtils.showMessage(request);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
