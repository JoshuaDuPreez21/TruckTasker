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
	
	public static String[] saveClient(int type) {
		String[] columns = null;
		
		if(type == 1) { 
			columns = new String[] {"name", "latitude", "longitude","cellNumber", "fax", "email","registrationNumber", "vatRegistrationNumber", "postalAddress", "generatedTime"};
		} else if(type == 2) {
			columns = new String[] {"name", "latitude", "longitude","cellNumber", "fax", "email","registrationNumber", "vatRegistrationNumber", "postalAddress", "editedTime"};
		} else {
			columns = new String[] {"id"}; 
		}
		 
		return columns;
	}
	
	public static String[] saveFleet(int type) {
		String[] columns = null;
		
		if(type == 1) {
			columns = new String[] {"alias", "reg", "model", "year", "vin", "frontTrailerReg", "rearTrailerReg", "generatedTime"};
		} else if(type == 2) {
			columns = new String[] {"alias", "reg", "model", "year", "vin", "frontTrailerReg", "rearTrailerReg", "editedTime"};
		} else {
			columns = new String[] {"id"};
		}

		return columns;
	}
	
	public static String[] saveDataTracker() {
		
		String[] columns = new String[] {"idUser", "idTechnician", "dataObject", "feature", "description", "generatedTime"};
		return columns;
	}
	
	public static String[] saveJobCard(int type) {
		String[] columns = null;
		
		if(type == 1) {
			columns = new String[] {"idFleet", "idClient", "idAdmin", "idTechnician", "siteName", "siteLatitude", "siteLongitude", "description", "estimatedTime", "startTime", "endTime", "hourlyRate", "totalCost", "jobStatus", "travelTime", "waitingTime", "travelKm", "orderNumber", "costCode", "generatedTime"};
		} else if(type == 2) {
			columns = new String[] {"idFleet", "idClient", "idAdmin", "idTechnician", "siteName", "siteLatitude", "siteLongitude", "description", "estimatedTime", "startTime", "endTime", "hourlyRate", "totalCost", "jobStatus", "travelTime", "waitingTime", "travelKm", "orderNumber", "costCode", "editedTime"};
		} else {
			columns = new String[] {"id"};
		}
 
		return columns;
	}
}
