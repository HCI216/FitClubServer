package com.nju.FitClubServer.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.Plan;

@Path("/planservice")
@Produces("application/xml")
public interface PlanService {

	@POST
	@Path("/askForPlan/{userID}")
	@Consumes("application/xml")
	public Plan askForPlan(@PathParam("userID") String userID);

	@GET
	@Path("/getPlan/{userID}")
	@Consumes("application/xml")
	public Plan getPlan(String userID);

}
