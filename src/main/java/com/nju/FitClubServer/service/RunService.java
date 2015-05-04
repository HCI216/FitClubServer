package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.RunRecord;

@Path("/runservice")
@Produces("application/xml")
public interface RunService {

	@POST
	@Path("/addRunRecord")
	@Consumes("application/xml")
	public boolean addRunRecord(RunRecord runRecord);

	@GET
	@Path("/getRunRecord/{userID}/{time}")
	@Consumes("application/xml")
	public ArrayList<RunRecord> getRunRecord(String userID, String time);

}
