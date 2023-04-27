package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import dao.TTDAO;
import helper.helper;

@Path("fleet")
public class fleetService {

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFleet(String body) {
		
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		String alias = null;
		String reg = null;
		String model = null;
		String year = null;
		String vin = null;
		String frontTrailerReg = null;
		String rearTrailerReg = null;
		
		if(jsonObject.has("alias") && !jsonObject.isNull("alias")) {
			alias = jsonObject.getString("alias");
		}
		
		if(jsonObject.has("reg") && !jsonObject.isNull("reg")) {
			reg = jsonObject.getString("reg");
		}
		
		if(jsonObject.has("model") && !jsonObject.isNull("model")) {
			model = jsonObject.getString("model");
		}
		
		if(jsonObject.has("year") && !jsonObject.isNull("year")) {
			year = jsonObject.getString("year");
		}
		
		if(jsonObject.has("vin") && !jsonObject.isNull("vin")) {
			vin = jsonObject.getString("vin");
		}
		
		if(jsonObject.has("frontTrailerReg") && !jsonObject.isNull("frontTrailerReg")) {
			frontTrailerReg = jsonObject.getString("frontTrailerReg");
		}	
		
		if(jsonObject.has("rearTrailerReg") && !jsonObject.isNull("rearTrailerReg")) {
			rearTrailerReg = jsonObject.getString("rearTrailerReg");
		}
		
		String generatedTime = helper.getCurrentDateTime();
		
		String[] columns = helper.saveFleet(1);
		Object[] values = {alias,reg,model,year,vin,frontTrailerReg,rearTrailerReg,generatedTime};
		
		Boolean saveFleet = TTDAO.saveData("fleet", columns, values);
		if(!saveFleet) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to save new fleet. Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();

	}
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFleet(String body) {
		
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		Long id = null;
		String alias = null;
		String reg = null;
		String model = null;
		String year = null;
		String vin = null;
		String frontTrailerReg = null;
		String rearTrailerReg = null;
		String editedTime = helper.getCurrentDateTime();
		
		if(jsonObject.has("id") && !jsonObject.isNull("id")) {
			id = jsonObject.getLong("id");
		}
		
		if(jsonObject.has("alias") && !jsonObject.isNull("alias")) {
			alias = jsonObject.getString("alias");
		}
		
		if(jsonObject.has("reg") && !jsonObject.isNull("reg")) {
			reg = jsonObject.getString("reg");
		}
		
		if(jsonObject.has("model") && !jsonObject.isNull("model")) {
			model = jsonObject.getString("model");
		}
		
		if(jsonObject.has("year") && !jsonObject.isNull("year")) {
			year = jsonObject.getString("year");
		}
		
		if(jsonObject.has("vin") && !jsonObject.isNull("vin")) {
			vin = jsonObject.getString("vin");
		}
		
		if(jsonObject.has("frontTrailerReg") && !jsonObject.isNull("frontTrailerReg")) {
			frontTrailerReg = jsonObject.getString("frontTrailerReg");
		}	
		
		if(jsonObject.has("rearTrailerReg") && !jsonObject.isNull("rearTrailerReg")) {
			rearTrailerReg = jsonObject.getString("rearTrailerReg");
		}
		
		String[] columns = helper.saveFleet(2);
		Object[] values = {alias,reg,model,year,vin,frontTrailerReg,rearTrailerReg,editedTime};
		String[] whereColumnNames = {"id"};
		Object[] whereValues = {id};
		
		Boolean saveFleet = TTDAO.updateData("fleet", columns, values, whereColumnNames, whereValues);
		if(!saveFleet) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to update fleet. Please contact IT support!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();

	}
}

