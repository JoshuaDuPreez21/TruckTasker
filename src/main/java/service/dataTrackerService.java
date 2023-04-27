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

@Path("dataTracker")
public class dataTrackerService {

	@Path("create")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createDataTracker(String body) {
		
		JSONObject jsonObject = new JSONObject(body);
		JSONObject errorObject = new JSONObject();
		
		Long idUser = null;
		Long idTechnician = null;
		String dataObject = null;
		String feature = null;
		String description = null;
		String generatedTime = helper.getCurrentDateTime();
		
		if(jsonObject.has("idUser") && !jsonObject.isNull("idUser")) {
			idUser = jsonObject.getLong("idUser");
		}
		
		if(jsonObject.has("idTechnician") && !jsonObject.isNull("idTechnician")) {
			idTechnician = jsonObject.getLong("idTechnician");
		}
		
		if(jsonObject.has("dataObject") && !jsonObject.isNull("dataObject")) {
			dataObject = jsonObject.getString("dataObject");
		}
		
		if(jsonObject.has("feature") && !jsonObject.isNull("feature")) {
			feature = jsonObject.getString("feature");
		}
		
		if(jsonObject.has("description") && !jsonObject.isNull("description")) {
			description = jsonObject.getString("description");
		}
			
		String[] columns = helper.saveDataTracker();
		Object[] values = {idUser,idTechnician,dataObject,feature,description,generatedTime};
		
		Boolean saveDataTracker = TTDAO.saveData("datatracker", columns, values);
		if(!saveDataTracker) {
			errorObject.put("errorCode", "-2");
			errorObject.put("message", "Failed to track activity!");
			return Response.status(200).entity(errorObject.toString()).build(); 
		}
		
		return Response.status(200).entity(jsonObject.toString()).build();

	}
}
