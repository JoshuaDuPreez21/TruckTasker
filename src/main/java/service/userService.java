package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import object.userObject;
import helper.helper;
import dao.TTDAO;

@Path("user")
public class userService {
	
	private static String table = "user";
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
		
		String fieldName = "email";
		Object fieldValue = email;
		Object[][] data = TTDAO.getDataByField(table, fieldName, fieldValue);
		List<userObject> user = null;
		if (data != null) {
		    for (Object[] row : data) {
		        Object userObject = row;
		        System.out.println(userObject);
		        
		    }
		} else {
			errorObject.put("errorCode", "-5");
			errorObject.put("message", "Invalid Email Address. Please try again or contact your Admin!");
		
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		
		
		
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
	@Produces(MediaType.APPLICATION_JSON)
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
		
		if(!validEmail(email)) {
			errorObject.put("errorCode", "-3");
			errorObject.put("message", "This email address already exists. Please try again or contact your Admin!");
			
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		if(!validCell(cellNumber)) {
			errorObject.put("errorCode", "-4");
			errorObject.put("message", "This cell number already exists. Please try again or contact your Admin!");
			
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		String generatedTime = helper.getCurrentDateTime();
		String[] columns = helper.saveUser(1);
		Object [] values = {name,surname,email,cellNumber,password,role,generatedTime};
		
		Boolean saveUser = TTDAO.saveData(table, columns, values);
		if(!saveUser) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to save new user.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build(); 
	}
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(String body) {
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		Long id = null;
		String name = null;
		String surname = null;
		String email = null;
		String cellNumber = null;
		String password = null;
		String role = null;// I, A, T
		
		if(jsonObject.has("id") && !jsonObject.isNull("id")) {
			id = jsonObject.getLong("id");
		}
		
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
		
		if(id == null) {
			errorObject.put("errorCode", "-1");
			errorObject.put("message", "User ID cannot be found. Please contact IT support!");
			
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		if(name == null || surname == null || email == null || cellNumber == null || password == null || role == null) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Please complete all fields!");
			
			return Response.status(200).entity(errorObject.toString()).build(); 
		
		}
		
		//check duplicate email + cell when updating
		
		// 1. find user by id 
		
		Object[] userObject = TTDAO.getDataById(table, id);
		String originalEmail = null;
		if(userObject != null) {
			originalEmail = (String) userObject[3];
		}
		
		
		boolean changedEmail = false;
		if(!originalEmail.equals(email)) {
			changedEmail = true;
		}
		
		if(changedEmail) {
			// 2.serach if any other user has this email
			// 3. if found then return code
			
			
		}
		
		
		String editedTime = helper.getCurrentDateTime();
		String[] columns = helper.saveUser(2);
		Object [] values = {name,surname,email,cellNumber,password,role,editedTime};
		String[] whereColumnNames = {"id"};
		Object [] idObject = {id};
		
		Boolean updateUser = TTDAO.updateData(table, columns, values , whereColumnNames, idObject);
		if(!updateUser) {
			errorObject.put("errorCode", "-3");
			errorObject.put("message", "Failed to update user.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build(); 
	}

	@Path("delete/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") Long id) {
		JSONObject errorObject = new JSONObject();
		
		if(id != null) {
			String[] columns = helper.saveUser(3);
			Object[] idObject = {id};
			Boolean deleteUser = TTDAO.deleteData(table, columns, idObject);
			
			
			if(!deleteUser) {
				errorObject.put("errorCode", "-2");
				errorObject.put("message", "Failed to delete user.Please contact IT support!");
				return Response.status(200).entity(errorObject.toString()).build(); 
			}
			
		}else {
			errorObject.put("errorCode", "-1");
			errorObject.put("message", "Failed to delete user.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",id);
		return Response.status(200).entity(jsonObject.toString()).build(); 
	}
	
	
	public Boolean validEmail(String email) {
		Boolean isValid = true;
		
		Object[][] data = TTDAO.getDataByField(table, "email", email);
		if (data != null) {
		    for (Object[] row : data) {
		    	isValid = false;
		        break;
		    }
		}
		
		return isValid;
	}
	
	public Boolean validCell(String cell) {
		Boolean isValid = true;
		
		Object[][] data = TTDAO.getDataByField(table, "cell", cell);
		if (data != null) {
		    for (Object[] row : data) {
		    	isValid = false;
		        break;
		    }
		}
		
		return isValid;
	}
}
