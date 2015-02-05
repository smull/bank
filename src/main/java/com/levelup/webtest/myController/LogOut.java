package com.levelup.webtest.myController;

import com.levelup.webtest.model.Controller;
import com.levelup.webtest.model.IController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 <servlet>
 <servlet-name>main</servlet-name>
 <servlet-class>com.levelup.webtest.MainServlet</servlet-class>
 </servlet>

 <servlet>
 <servlet-name>insert</servlet-name>
 <servlet-class>com.levelup.webtest.RowInsertServlet</servlet-class>
 </servlet>

 <servlet>
 <servlet-name>login</servlet-name>
 <servlet-class>com.levelup.webtest.LoginServlet</servlet-class>
 </servlet>

 <servlet>
 <servlet-name>logout</servlet-name>
 <servlet-class>com.levelup.webtest.LogOutServlet</servlet-class>
 </servlet>


 <servlet-mapping>
 <servlet-name>main</servlet-name>
 <url-pattern>/query</url-pattern>
 </servlet-mapping>

 <servlet-mapping>
 <servlet-name>insert</servlet-name>
 <url-pattern>/insert</url-pattern>
 </servlet-mapping>

 <servlet-mapping>
 <servlet-name>login</servlet-name>
 <url-pattern>/login</url-pattern>
 </servlet-mapping>

 <servlet-mapping>
 <servlet-name>logout</servlet-name>
 <url-pattern>/logout</url-pattern>
 </servlet-mapping>

 * Created by user on 03.02.2015.
 */


@Controller(nameController = "controller")
public class LogOut implements IController,Serializable {

    @Override
    public String service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("user");
        //response.sendRedirect("content.jsp");
        return "content.jsp";
    }
}
