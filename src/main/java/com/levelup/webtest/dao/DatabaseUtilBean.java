package com.levelup.webtest.dao;

import com.levelup.webtest.Constants;
import com.levelup.webtest.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by denis_zavadsky on 1/20/15.
 */
public class DatabaseUtilBean {
    private Connection connection;

    public DatabaseUtilBean() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            //DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/TestDB");
            DataSource ds = (DataSource) envContext.lookup("jdbc/TestDB");
            connection = ds.getConnection();
        } catch (NamingException ne){
            ne.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    public ModelResult selectFromTable(String tableName){
        ModelResult result = new ModelResult();
        try {
            String query = "SELECT * FROM " + tableName;

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<String> columns = new LinkedList<String>();

            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                columns.add(columnName);
            }

            List<List<String>> rowsList = new LinkedList<List<String>>();
            while (rs.next()){
                LinkedList<String> row = new LinkedList<String>();
                for (int i=1; i<=columns.size(); i++) {
                    String value = rs.getString(i);
                    row.add(value);
                }
                rowsList.add(row);
            }

            result.setTableName(tableName);
            result.setColumns(columns);
            result.setValues(rowsList);

            rs.close();
            stmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public void insertDataIntoTable(String tableName, List<String> columns, List<String> values){
        StringBuffer buffer  = new StringBuffer();
        buffer.append("INSERT INTO " + tableName +"(");
        boolean isFirst = true;
        for (String columnName: columns){
            if (isFirst){
                buffer.append(columnName);
                isFirst = false;
            } else {
                buffer.append(","+columnName);
            }
        }
        buffer.append(") VALUES(");
        isFirst = true;
        for (String value: values){
            if (isFirst){
                buffer.append("'"+value+"'");
                isFirst = false;
            } else {
                buffer.append(",'"+value+"'");
            }
        }
        buffer.append(");");
        String insertQuery = buffer.toString();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(insertQuery);

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User login(String email, String password){
        User user = null;
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(Constants.LOGIN_USER_QUERY);
            stmt.setString(1,email);

            ResultSet rs = stmt.executeQuery();


            if (rs.next()){
                user = new User();
                user.fillUpFromResultSet(rs);

                if (!user.getPassword().equals(password)){
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
