package ford.pivotaltracker.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ford.pivotaltracker.analyze.Analyzer;
import ford.pivotaltracker.analyze.Report;
import ford.pivotaltracker.read.ResultStore;

@Path("report")
public class ReportService {

	@Path("/hello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "hey";
	}
	
	@PUT
	@Path("/refresh")
	public Response refresh() {
		try {
			ResultStore.refresh();
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@Path("/project/{projId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Report projectStatus(@PathParam("projId") String projId) {
	
		return ResultStore
						  .transform(Analyzer.aggregateStoryCount)
						  .get(projId); 

	}
	
	
}
