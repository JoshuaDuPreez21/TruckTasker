package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class userService {
	
	
	@Path("")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public static Response login() {
	
		
	
	}

}
