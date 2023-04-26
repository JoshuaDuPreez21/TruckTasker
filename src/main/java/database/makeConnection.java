package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class makeConnection {

	
	public Boolean createConnection() {
		Boolean toReturn = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trucktasker", "root", "adminadmin");
			Statement stmt = con.createStatement();
			System.out.println(stmt);
			
			toReturn = true;
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return toReturn;
	}
	
	
	// generic classes for CRUD
	
	
	public void delete() {
		if(createConnection()) {
			//delete - check how to keep connection open and then close after activity
		}
	}
}
