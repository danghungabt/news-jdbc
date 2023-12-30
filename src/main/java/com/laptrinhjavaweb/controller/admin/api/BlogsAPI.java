package com.laptrinhjavaweb.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.BlogsModel;
import com.laptrinhjavaweb.model.PagingModel;
import com.laptrinhjavaweb.model.response.BlogsResponseModel;
import com.laptrinhjavaweb.service.IBlogsService;
import com.laptrinhjavaweb.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api-admin-blogs", "/api-admin-blogs/*"})
public class BlogsAPI extends HttpServlet {

    @Inject
    private IBlogsService blogsService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String pathInfo = request.getPathInfo();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (pathInfo != null && pathInfo.equals("/insertAll")) {
            List<BlogsModel> blogsModelList = HttpUtil.of(request.getReader()).toListModel(BlogsModel.class);
            List<BlogsResponseModel> results = blogsService.insertAll(blogsModelList);
            mapper.writeValue(response.getOutputStream(), results);
        } else {
            BlogsModel blogsModel = HttpUtil.of(request.getReader()).toModel(BlogsModel.class);
//        newsModel.setCreatedBy(((UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL")).getUserName());
            BlogsResponseModel result = blogsService.insert(blogsModel);
            mapper.writeValue(response.getOutputStream(), result);
        }
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
                    BlogsResponseModel blog = blogsService.findOneClient(Long.parseLong(id));
                    if (blog != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), blog);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Blog not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide blog ID");
                }
            }
        } else if (pathInfo != null && pathInfo.startsWith("/findOneBySlug")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length >= 3) {
                String slug = pathParts[2];
                if (slug != null) {
                    // Xử lý API findOne với id
                    BlogsResponseModel blog = blogsService.findOneBySlugClient(slug);
                    if (blog != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), blog);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Blog not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide blog slug");
                }
            }
        } else if (pathInfo != null && pathInfo.startsWith("/findByCategoryId")) {
            String[] pathParts = pathInfo.split("/");
            String page = request.getParameter("page");
            if (pathParts.length >= 3) {
                String categoryId = pathParts[2];
                if (categoryId != null) {
                    // Xử lý API findOne với id
//                    List<BlogsResponseModel> blogs = blogsService.findByCategoryIdClient(Long.parseLong(categoryId));
                    PagingModel<BlogsResponseModel> blogs = blogsService.findByCategoryIdClientWithPageable(Long.parseLong(categoryId),
                                                                                                    Integer.parseInt(page));
                    if (blogs != null) {
                        // Gửi response với dữ liệu của category tương ứng
                        response.setContentType("application/json");
                        mapper.writeValue(response.getOutputStream(), blogs);
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("Category Id not found");
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Please provide category Id slug");
                }
            }
        } else if (pathInfo != null && pathInfo.equals("/search")) {
            String key = request.getParameter("key");
            String page = request.getParameter("page");

            if (key != null) {
                // Xử lý API findOne với id
//                List<BlogsResponseModel> blogs = blogsService.findByKeyClient(key);
                PagingModel<BlogsResponseModel> blogs = blogsService.findByKeyClientWithPageable(key, Integer.parseInt(page));
                if (blogs != null) {
                    // Gửi response với dữ liệu của category tương ứng
                    response.setContentType("application/json");
                    mapper.writeValue(response.getOutputStream(), blogs);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Blog is not found");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Please provide key to search");
            }

        }else if (pathInfo != null && pathInfo.equals("/recent")) {

                // Xử lý API findOne với id
//                List<BlogsResponseModel> blogs = blogsService.findByKeyClient(key);
                List<BlogsResponseModel> blogs = blogsService.getRecent();
                if (blogs != null) {
                    // Gửi response với dữ liệu của category tương ứng
                    response.setContentType("application/json");
                    mapper.writeValue(response.getOutputStream(), blogs);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("Blog is not found");
                }


        } else {
            String page = request.getParameter("page");

            PagingModel<BlogsResponseModel> blogsResponseModels = blogsService.findAllClientWithPageable(Integer.parseInt(page));
            if (blogsResponseModels != null) {
                response.setContentType("application/json");
                mapper.writeValue(response.getOutputStream(), blogsResponseModels);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Blog not found");
            }
        }
    }

}
