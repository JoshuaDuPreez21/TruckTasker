package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import object.userObject;
import helper.helper;
import dao.TTDAO;

@Path("user")
public class userService {
	
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response login(String body) {
	
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		String email = jsonObject.getString("email");
		String password = jsonObject.getString("password");
		
		if(email == null) {
			errorObject.put("errorCode", "-1");
			errorObject.put("message", "Invalid email address. Please contact your Admin!");
		
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		if(password == null) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Invalid password. Please contact your Admin!");
		
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
	
		
		//find user by field
		List<userObject> user = new ArrayList<userObject>();
		if(user != null && user.size() > 0) {
			userObject userData = user.get(0);
			if(userData != null) {
				
				String userPassword = password;
				String savedPassword = userData.getPassword();
				
				if(!userPassword.equals(savedPassword)) { // HASH passwords later
					errorObject.put("errorCode", "-4");
					errorObject.put("message", "Incorrect Password. Please contact your Admin!");
				
					return Response.status(200).entity(errorObject.toString()).build(); 
				}else {
					
					 jsonObject = new JSONObject();
					 jsonObject.put("data", userData);
				}
				
			}
		}else {
			errorObject.put("errorCode", "-3");
			errorObject.put("message", "User not does exist. Please contact your Admin!");
		
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build(); 
	}
	
	
	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(String body) {
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		String name = null;
		String surname = null;
		String email = null;
		String cellNumber = null;
		String password = null;
		String role = null;// I, A, T
		
		
		if(jsonObject.has("name") && !jsonObject.isNull("name")) {
			name = jsonObject.getString("name");
		}
		
		if(jsonObject.has("surname") && !jsonObject.isNull("surname")) {
			surname = jsonObject.getString("surname");
		}
		
		if(jsonObject.has("email") && !jsonObject.isNull("email")) {
			email = jsonObject.getString("email");
		}
		
		if(jsonObject.has("cellNumber") && !jsonObject.isNull("cellNumber")) {
			cellNumber = jsonObject.getString("cellNumber");
		}
		
		if(jsonObject.has("password") && !jsonObject.isNull("password")) {
			password = jsonObject.getString("password");
		}
		if(jsonObject.has("role") && !jsonObject.isNull("role")) {
			role = jsonObject.getString("role");
		}
		
		
		
		if(name == null || surname == null || email == null || cellNumber == null || password == null || role == null) {
			errorObject.put("errorCode", "-1");
			errorObject.put("message", "Please complete all fields!");
			
			return Response.status(200).entity(errorObject.toString()).build(); 
		
		}
		
		String generatedTime = helper.getCurrentDateTime();
		String[] columns = helper.saveUser();
		Object [] values = {name,surname,email,cellNumber,password,role,generatedTime};
		
		Boolean saveUser = TTDAO.saveData("user", columns, values);
		if(!saveUser) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to save new user.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build(); 
	}

}
