package com.levelup.webtest;

import com.levelup.webtest.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SMULL on 01.02.2015.
 */
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        StringBuffer url = request.getRequestURL();
        if (url.toString().endsWith("content.jsp")) {
            User user = (User) request.getSession().getAttribute("user");

            if (user != null) {
                //filterChain.doFilter(servletRequest, servletResponse);

                if (user.getRole().equalsIgnoreCase(Constants.LOGIN_ROLE_PARAMETER_ADMIN)) {
                    ((HttpServletResponse) servletResponse).sendRedirect("admin.jsp");
                } else if (user.getRole().equalsIgnoreCase(Constants.LOGIN_ROLE_PARAMETER_USER)) {
                    ((HttpServletResponse) servletResponse).sendRedirect("user.jsp");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }

        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
