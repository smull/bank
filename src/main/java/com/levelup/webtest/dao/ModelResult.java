package com.levelup.webtest.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by denis_zavadsky on 1/20/15.
 */
public class ModelResult implements Serializable{

    private String tableName;
    private List<String> columns;
    private List<List<String>> values;

    public ModelResult() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }
}
