package com.levelup.webtest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by denis_zavadsky on 1/13/15.
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.getWriter().println("<html>");
        response.getWriter().println("<head></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div>");
        response.getWriter().println("Query Parameter is: "+query);
        response.getWriter().println("</div>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");


    }
}
