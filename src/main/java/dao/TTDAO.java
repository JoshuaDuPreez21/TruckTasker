package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import object.clientObject;

public class TTDAO<T extends clientObject> {
	private Connection conn;

    public TTDAO(Connection conn) {
        this.conn = conn;
    }

    public List<T> getAll(String tableName, Class<T> classs) throws SQLException {
        List<T> entities = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            T entity = null;
            try {
                entity = classs.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            
        }
        return entities;
    }

    public T getById(String tableName, int id, Class<T> classs) throws SQLException {
        T entity = null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);
        if (rs.next()) {
            try {
                entity = classs.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            
        }
        return entity;
    }

    public List<T> getByColumn(String tableName, String columnName, String columnValue, Class<T> classs) throws SQLException {
        List<T> entities = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " = '" + columnValue + "'");
        while (rs.next()) {
            T entity = null;
            try {
                entity = classs.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return entities;
    }
}
