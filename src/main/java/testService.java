import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import dao.TTDAO;
import database.makeConnection;
import object.clientObject;

@Path("test")
public class testService {

	@Path("test")
	@GET
	public static void test() {
		System.out.println("TEST");
		
		//Update example
		
		/*String tableName = "client";
		String[] columnNames = {"name", "latitude", "longitude","cellNumber", "fax", "email","registrationNumber", "vatRegistrationNumber", "postalAddress", "generatedTime"};
		Object[] values = {"Truck Tasker", "30", "40", "0110000000", "012", "john@gmail.com", "123", "456", "9 Eileen", "2023-04-27 09:00:00"};
		String[] whereColumnNames = {"id"};
		Object[] whereValues = {1};
		boolean success = TTDAO.updateData(tableName, columnNames, values, whereColumnNames, whereValues);
		if (success) {
		    System.out.println("Data updated successfully!");
		} else {
		    System.out.println("Failed to update data.");
		}*/
		
		//get all example
		
		/*String tableName = "client";
		List<Object[]> dataList = TTDAO.getAllData(tableName);
		for (Object[] data : dataList) {
		    System.out.println(Arrays.toString(data));
		}*/
		
		//find by id example
		
		/*String tableName = "client";
		int id = 1;
		Object[] data = TTDAO.getDataById(tableName, id);
		if (data != null) {
		    System.out.println(Arrays.toString(data));
		} else {
		    System.out.println("No data found for id " + id);
		}*/
		
		//find by field
		
		/*String tableName = "client";
		String fieldName = "name";
		Object fieldValue = "Vybz";
		Object[][] data = TTDAO.getDataByField(tableName, fieldName, fieldValue);
		if (data != null) {
		    for (Object[] row : data) {
		        System.out.println(Arrays.toString(row));
		    }
		} else {
		    System.out.println("No data found for field " + fieldName + " with value " + fieldValue);
		}*/
		
		//special query example
		
		/*String query = "SELECT * FROM client WHERE longitude < ?";
		Object[] params = { 30 };
		Object[][] data = TTDAO.executeQuery(query, params);
		if (data != null) {
		    for (Object[] row : data) {
		        System.out.println(Arrays.toString(row));
		    }
		} else {
		    System.out.println("No data found for query " + query);
		}*/
	}
}
