package helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class helper {

	public static String getCurrentDateTime() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);

		return strDate;
	}
	
	public static String[] saveUser(int type) {
		String[] columns = null;
		
		if(type == 1) { // create
			columns = new String[] {"name", "surname", "email", "cellNumber", "password", "role", "generatedTime"}; 
		}else if(type == 2) { // update
			columns = new String[] {"name", "surname", "email", "cellNumber", "password", "role", "editedTime"}; 
		}else {
			columns = new String[] {"id"}; 
		}
		
		return columns;
	}
	
	
	
	
	public static String[] saveClient() {
		
		String[] columns = new String[] {"name", "latitude", "longitude","cellNumber", "fax", "email","registrationNumber", "vatRegistrationNumber", "postalAddress", "generatedTime"};
		return columns;
	}
	
	public static String[] saveFleet() {
		
		String[] columns = new String[] {"alias", "reg", "model", "year", "vin", "frontTrailerReg", "rearTrailerReg", "generatedTime"};
		return columns;
	}
	
	public static String[] saveDataTracker() {
		
		String[] columns = new String[] {"idUser", "idTechnician", "dataObject", "feature", "description", "generatedTime"};
		return columns;
	}
	
	public static String[] saveJobCard() {
		
		String[] columns = new String[] {"idFleet", "idClient", "idAdmin", "idTechnician", "siteName", "siteLatitude", "siteLongitude", "description", "estimatedTime", "startTime", "endTime", "hourlyRate", "totalCost", "jobStatus", "travelTime", "waitingTime", "travelKm", "orderNumber", "costCode", "generatedTime"};
		return columns;
	}
	
	public static String[] updateUser() {
		
		String[] columns = new String[] {"name", "surname", "email", "cellNumber", "password", "role", "editedTime"};
		return columns;
	}
	
	public static String[] updateClient() {
		
		String[] columns = new String[] {"name", "latitude", "longitude","cellNumber", "fax", "email","registrationNumber", "vatRegistrationNumber", "postalAddress", "editedTime"};
		return columns;
	}
	
	public static String[] updateFleet() {
		
		String[] columns = new String[] {"alias", "reg", "model", "year", "vin", "frontTrailerReg", "rearTrailerReg", "editedTime"};
		return columns;
	}
	
	public static String[] updateJobCard() {
		
		String[] columns = new String[] {"idFleet", "idClient", "idAdmin", "idTechnician", "siteName", "siteLatitude", "siteLongitude", "description", "estimatedTime", "startTime", "endTime", "hourlyRate", "totalCost", "jobStatus", "travelTime", "waitingTime", "travelKm", "orderNumber", "costCode", "editedTime"};
		return columns;
	}
}
