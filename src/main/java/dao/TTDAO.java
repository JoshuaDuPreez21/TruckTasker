package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import object.clientObject;

public class TTDAO<T extends clientObject> {
 
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/trucktasker";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "tristan";
    
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
    
    public static boolean deleteData(String tableName, String[] columnNames, Object[] values) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            StringBuilder sql = new StringBuilder("DELETE FROM " + tableName + " WHERE ");
            for (int i = 0; i < columnNames.length; i++) {
                sql.append(columnNames[i] + " = ?");
                if (i < columnNames.length - 1) {
                    sql.append(" AND ");
                }
            }
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
    
    public static boolean updateData(String tableName, String[] columnNames, Object[] values, String[] whereColumnNames, Object[] whereValues) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
            for (int i = 0; i < columnNames.length; i++) {
                sql.append(columnNames[i] + " = ?");
                if (i < columnNames.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(" WHERE ");
            for (int i = 0; i < whereColumnNames.length; i++) {
                sql.append(whereColumnNames[i] + " = ?");
                if (i < whereColumnNames.length - 1) {
                    sql.append(" AND ");
                }
            }
            
            statement = connection.prepareStatement(sql.toString());
            int parameterIndex = 1;
            for (Object value : values) {
                statement.setObject(parameterIndex++, value);
            }
            for (Object whereValue : whereValues) {
                statement.setObject(parameterIndex++, whereValue);
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
    
    public static List<Object[]> getAllData(String tableName) {
        List<Object[]> dataList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            String sql = "SELECT * FROM " + tableName;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                Object[] data = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    data[i - 1] = resultSet.getObject(i);
                }
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return dataList;
    }
    
    public static Object[] getDataById(String tableName, Long id) {
        Object[] data = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                data = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    data[i - 1] = resultSet.getObject(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return data;
    }
    
    public static Object[][] getDataByField(String tableName, String fieldName, Object fieldValue) {
        Object[][] data = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            String sql = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, fieldValue);
            resultSet = statement.executeQuery();
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                int columnCount = resultSet.getMetaData().getColumnCount();
                data = new Object[rowCount][columnCount];
                resultSet.beforeFirst();
                int i = 0;
                while (resultSet.next()) {
                    for (int j = 1; j <= columnCount; j++) {
                        data[i][j - 1] = resultSet.getObject(j);
                    }
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return data;
    }
    
    public static Object[][] executeQuery(String query, Object[] params) {
        Object[][] data = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.prepareStatement(query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
            }
            resultSet = statement.executeQuery();
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                int columnCount = resultSet.getMetaData().getColumnCount();
                data = new Object[rowCount][columnCount];
                resultSet.beforeFirst();
                int i = 0;
                while (resultSet.next()) {
                    for (int j = 1; j <= columnCount; j++) {
                        data[i][j - 1] = resultSet.getObject(j);
                    }
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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
        return data;
    }
}


