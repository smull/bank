package com.levelup.webtest.myController;

import com.levelup.webtest.Constants;
import com.levelup.webtest.dao.DatabaseUtilBean;
import com.levelup.webtest.model.Controller;
import com.levelup.webtest.model.IController;
import com.levelup.webtest.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 <filter>
 <filter-name>security</filter-name>
 <filter-class>com.levelup.webtest.SecurityFilter</filter-class>
 </filter>

 <filter>
 <filter-name>role</filter-name>
 <filter-class>com.levelup.webtest.RoleFilter</filter-class>
 </filter>

 <filter-mapping>
 <filter-name>security</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>

 <filter-mapping>
 <filter-name>role</filter-name>
 <url-pattern>/*</url-pattern>
 </filter-mapping>

 * Created by user on 03.02.2015.
 */

@Controller(nameController = "controller")
public class Login implements IController,Serializable {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(Constants.LOGIN_EMAIL_PARAMETER);
        String password = request.getParameter(Constants.LOGIN_PASSWORD_PARAMETER);

        DatabaseUtilBean databaseUtilBean = new DatabaseUtilBean();
        User user = databaseUtilBean.login(login,password);
        if (user!=null){
            request.getSession().setAttribute("user",user);
            response.sendRedirect("content.jsp");
            return "content.jsp";
        } else {
            //response.sendRedirect("newlogin.jsp");
            return "newlogin.jsp";
        }
    }
}
