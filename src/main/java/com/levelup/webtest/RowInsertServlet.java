package com.levelup.webtest;

import com.levelup.webtest.dao.DatabaseUtilBean;
import com.levelup.webtest.dao.ModelResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis_zavadsky on 1/20/15.
 */
public class RowInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelResult result = (ModelResult) request.getSession().getAttribute("result");
        boolean isError = false;
        if (result!=null){
            LinkedList<String> values = new LinkedList<String>();
            for (String columnName: result.getColumns()){
                String value = request.getParameter(columnName);

                if (!columnName.equalsIgnoreCase("id")){
                    if (value==null || value.isEmpty()){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        isError = true;
                        break;
                    }
                    values.add(value);
                }

            }
            List<String> columns = new LinkedList<String>();
            columns.addAll(result.getColumns());
            columns.remove("id");
            if (!isError) {
                DatabaseUtilBean dbUtilBean = new DatabaseUtilBean();
                dbUtilBean.insertDataIntoTable(result.getTableName(), columns, values);
                response.setStatus(HttpServletResponse.SC_OK);
            }
            //response.sendRedirect("show_table.jsp?table="+result.getTableName());
        }

    }
}
