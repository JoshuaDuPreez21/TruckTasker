package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import dao.TTDAO;

@Path("jobCard")
public class jobCardService {
	
	private static String table = "jobCard";

	@Path("syncJobs/{idUser}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response syncJobs(@PathParam("idUser") Long idTechnician) {
		
		String cutsomQuery = "select * from jobcards where idTechnician = "+idTechnician+" and jobStatus <> 'C' order by generatedTime asc";
		JSONArray jsonArray = TTDAO.customQueryJobCard(table, cutsomQuery);
		
		if(jsonArray != null && jsonArray.length() > 0) {
			for(int i = 0 ; i < jsonArray.length(); i++) {
				System.out.println("TEST");
			}
		}
		
		return null;
	}
}
