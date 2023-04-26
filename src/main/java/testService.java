import javax.ws.rs.GET;
import javax.ws.rs.Path;

import database.makeConnection;

@Path("test")
public class testService {

	@Path("test")
	@GET
	public static void test() {
		System.out.println("TEST");
		
		makeConnection mk = new makeConnection();
		mk.testConnection();
	}
}
