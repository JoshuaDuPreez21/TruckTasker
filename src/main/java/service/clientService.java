package service;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import dao.TTDAO;
import helper.helper;

@Path("client")
public class clientService {

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(String body) {
		
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		String name = null;
		String latitude = null;
		String longitude = null;
		String cellNumber = null;
		String fax = null;
		String email = null;
		String registrationNumber = null;
		String vatRegistrationNumber = null;
		String postalAddress = null;
		String generatedTime = null;
		
		if(jsonObject.has("name") && !jsonObject.isNull("name")) {
			name = jsonObject.getString("name");
		}
		
		if(jsonObject.has("latitude") && !jsonObject.isNull("latitude")) {
			latitude = jsonObject.getString("latitude");
		}
		
		if(jsonObject.has("longitude") && !jsonObject.isNull("longitude")) {
			longitude = jsonObject.getString("longitude");
		}
		
		if(jsonObject.has("cellNumber") && !jsonObject.isNull("cellNumber")) {
			cellNumber = jsonObject.getString("cellNumber");
		}
		
		if(jsonObject.has("fax") && !jsonObject.isNull("fax")) {
			fax = jsonObject.getString("fax");
		}
		
		if(jsonObject.has("email") && !jsonObject.isNull("email")) {
			email = jsonObject.getString("email");
		}	
		
		if(jsonObject.has("registrationNumber") && !jsonObject.isNull("registrationNumber")) {
			registrationNumber = jsonObject.getString("registrationNumber");
		}
		
		if(jsonObject.has("vatRegistrationNumber") && !jsonObject.isNull("vatRegistrationNumber")) {
			vatRegistrationNumber = jsonObject.getString("vatRegistrationNumber");
		}
		
		if(jsonObject.has("postalAddress") && !jsonObject.isNull("postalAddress")) {
			postalAddress = jsonObject.getString("postalAddress");
		}	
		
		generatedTime = helper.getCurrentDateTime();
		
		String[] columns = helper.saveClient();
		Object[] values = {name,latitude,longitude,cellNumber,fax,email,registrationNumber,vatRegistrationNumber,postalAddress,generatedTime};
		
		Boolean saveClient = TTDAO.saveData("client", columns, values);
		if(!saveClient) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to save new client.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();

	}
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateClient(String body) {
		
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		Long id = null;
		String name = null;
		String latitude = null;
		String longitude = null;
		String cellNumber = null;
		String fax = null;
		String email = null;
		String registrationNumber = null;
		String vatRegistrationNumber = null;
		String postalAddress = null;
		String generatedTime = null;
		
		if(jsonObject.has("id") && !jsonObject.isNull("id")) {
			id = jsonObject.getLong("id");
		}
		
		if(jsonObject.has("name") && !jsonObject.isNull("name")) {
			name = jsonObject.getString("name");
		}
		
		if(jsonObject.has("latitude") && !jsonObject.isNull("latitude")) {
			latitude = jsonObject.getString("latitude");
		}
		
		if(jsonObject.has("longitude") && !jsonObject.isNull("longitude")) {
			longitude = jsonObject.getString("longitude");
		}
		
		if(jsonObject.has("cellNumber") && !jsonObject.isNull("cellNumber")) {
			cellNumber = jsonObject.getString("cellNumber");
		}
		
		if(jsonObject.has("fax") && !jsonObject.isNull("fax")) {
			fax = jsonObject.getString("fax");
		}
		
		if(jsonObject.has("email") && !jsonObject.isNull("email")) {
			email = jsonObject.getString("email");
		}	
		
		if(jsonObject.has("registrationNumber") && !jsonObject.isNull("registrationNumber")) {
			registrationNumber = jsonObject.getString("registrationNumber");
		}
		
		if(jsonObject.has("vatRegistrationNumber") && !jsonObject.isNull("vatRegistrationNumber")) {
			vatRegistrationNumber = jsonObject.getString("vatRegistrationNumber");
		}
		
		if(jsonObject.has("postalAddress") && !jsonObject.isNull("postalAddress")) {
			postalAddress = jsonObject.getString("postalAddress");
		}	
		
		generatedTime = helper.getCurrentDateTime();
		
		String[] columns = helper.saveClient();
		Object[] values = {name,latitude,longitude,cellNumber,fax,email,registrationNumber,vatRegistrationNumber,postalAddress,generatedTime};
		String[] whereColumnNames = {"id"};
		Object[] whereValues = {1};
		
		Boolean saveClient = TTDAO.updateData("client", columns, values, whereColumnNames, whereValues);
		if(!saveClient) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to update client.Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();

	}
}

