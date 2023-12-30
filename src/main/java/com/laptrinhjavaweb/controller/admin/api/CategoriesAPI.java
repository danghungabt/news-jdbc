package com.laptrinhjavaweb.controller.admin.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.converter.ICategoriesConverter;
import com.laptrinhjavaweb.model.CategoriesModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.CategoriesResponseModel;
import com.laptrinhjavaweb.service.ICategoriesService;
import com.laptrinhjavaweb.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-categories", "/api-admin-categories/*"})
public class CategoriesAPI extends HttpServlet {
    @Inject
    private ICategoriesService categoriesService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CategoriesModel categoriesModel = HttpUtil.of(request.getReader()).toModel(CategoriesModel.class);
//        newsModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
        CategoriesResponseModel result = categoriesService.insert(categoriesModel);
        mapper.writeValue(response.getOutputStream(), result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        ObjectMapper mapper = new ObjectMapper();
        if (pathInfo != null && pathInfo.startsWith("/findOneById")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length >= 3) {
                String id = pathParts[2];
                if (id != null) {
                    // Xử lý API findOne với id
                    CategoriesResponseModel category = categoriesService.findOneClient(Long.parseLong(id));
                    if (category != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), category);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Category not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide category ID");
                }
            }
        } else if (pathInfo != null && pathInfo.startsWith("/findOneBySlug")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length >= 3) {
                String slug = pathParts[2];
                if (slug != null) {
                    // Xử lý API findOne với id
                    CategoriesResponseModel category = categoriesService.findOneBySlugClient(slug);
                    if (category != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), category);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Category not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide category slug");
                }
            }
        } else {
            String page = request.getParameter("page");

            PagingModel<CategoriesResponseModel> categoriesResponseModels = categoriesService.findAllClientWithPageable(Integer.parseInt(page));
            if (categoriesResponseModels != null) {
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), categoriesResponseModels);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Category not found");
            }
        }
    }
}
