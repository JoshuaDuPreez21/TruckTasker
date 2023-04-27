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
	
	public static String[] saveUser() {
		
		String[] columns = new String[] {"name", "surname", "email", "cellNumber", "password", "role", "generatedTime"}; 
		return columns;
	}
}
