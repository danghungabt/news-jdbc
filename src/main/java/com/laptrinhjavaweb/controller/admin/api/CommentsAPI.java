package com.laptrinhjavaweb.controller.admin.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.CommentsModel;
import com.laptrinhjavaweb.model.TreeNodeModel;
import com.laptrinhjavaweb.model.response.CommentsResponseModel;
import com.laptrinhjavaweb.service.ICommentsService;
import com.laptrinhjavaweb.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-comments", "/api-admin-comments/*"})
public class CommentsAPI extends HttpServlet {
    @Inject
    private ICommentsService commentsService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        CommentsModel commentsModel = HttpUtil.of(request.getReader()).toModel(CommentsModel.class);
        CommentsResponseModel result = commentsService.insert(commentsModel);
        mapper.writeValue(response.getOutputStream(), result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        ObjectMapper mapper = new ObjectMapper();
        if (pathInfo != null && pathInfo.startsWith("/findOneByBlogId")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length >= 3) {
                String id = pathParts[2];
                if (id != null) {
                    // Xử lý API findOne với id
                    List<TreeNodeModel<CommentsResponseModel>> treeNodeModels = commentsService.findByBlogId(Long.parseLong(id));
                    if (treeNodeModels != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), treeNodeModels);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Category not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide category ID");
                }
            }
        } else if (pathInfo != null && pathInfo.equals("/recent")) {

            // Xử lý API findOne với id
            List<CommentsResponseModel> comments = commentsService.getRecent();
            if (comments != null) {
                // Gửi response với dữ liệu của category tương ứng
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), comments);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Category not found");
            }
        } else {
            /*List<CategoriesResponseModel> categoriesResponseModels = categoriesService.findAllClient();
            if (categoriesResponseModels != null) {
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), categoriesResponseModels);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Category not found");
            }*/
        }
    }
}
