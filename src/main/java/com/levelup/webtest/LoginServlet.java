package com.levelup.webtest;

import com.levelup.webtest.dao.DatabaseUtilBean;
import com.levelup.webtest.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by denis_zavadsky on 1/31/15.
 */
public class LoginServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(Constants.LOGIN_EMAIL_PARAMETER);
        String password = request.getParameter(Constants.LOGIN_PASSWORD_PARAMETER);

        DatabaseUtilBean databaseUtilBean = new DatabaseUtilBean();
        User user = databaseUtilBean.login(login,password);
        if (user!=null){
            request.getSession().setAttribute("user",user);
            response.sendRedirect("content.jsp");
        } else {
            response.sendRedirect("newlogin.jsp");
        }
    }
}
