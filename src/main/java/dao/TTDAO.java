package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/trucktasker";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "adminadmin";
    
    public static boolean saveData(String tableName, String[] columnNames, Object[] values) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
            for (int i = 0; i < columnNames.length; i++) {
                sql.append(columnNames[i]);
                if (i < columnNames.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") VALUES (");
            for (int i = 0; i < values.length; i++) {
                sql.append("?");
                if (i < values.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");
            statement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }
}

