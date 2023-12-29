package com.laptrinhjavaweb.filter;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin", "*"); // Đây là cấu hình cho phép tất cả các nguồn (*), bạn có thể thay thế bằng nguồn cụ thể của mình.
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

        String url = request.getRequestURI();
        if(url.startsWith("/admin")){
            UserModel model = (UserModel) SessionUtils.getInstance().getValue(request,"USERMODEL");
            if(model != null){
                if(model.getRole().getCode().equals(SystemConstant.ADMIN)){
                    filterChain.doFilter(servletRequest,servletResponse);
                } else if(model.getRole().getCode().equals(SystemConstant.USER)){
                    response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
                }
            }else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
