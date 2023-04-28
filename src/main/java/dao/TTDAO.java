package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import object.clientObject;

public class TTDAO<T extends clientObject> {
 
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
    
    public static JSONArray getAllData(String tableName) {
        JSONArray jsonArray = new JSONArray();
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
                jsonArray.put(data);
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
        return jsonArray;
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
    
    
    public static JSONArray customQueryJobCard(String tableName, String query) {
        
    	JSONArray jsonArray = new JSONArray();
    	try{
    		Connection conn;
    		conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    		Statement st = conn.createStatement();
    	    ResultSet rs = st.executeQuery(query);
    	      
    	    while (rs.next())
    	    { // USE STATIC BUILD JSONOBJECT HERE
    	        Long id = rs.getLong("id");
    	        String siteName = rs.getString("siteName");
    	        String description = rs.getString("description");
    	        
    	        System.out.println(id + "  "+ siteName + "  "+ description);
    	        
    	        
    	        JSONObject jsonObject = new JSONObject();
    	        jsonArray.put(jsonObject);
    	        
    	     }
    	     st.close();
    	    }catch (Exception e){
    	      System.err.println("Got an exception! ");
    	      System.err.println(e.getMessage());
    	    }
			return jsonArray;
    	}
    
    
    
	}


