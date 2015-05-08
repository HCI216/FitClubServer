package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.nju.FitClubServer.model.RunRecord;
import com.nju.FitClubServer.model.RunRecordList;

@Path("/runservice")
@Produces("application/xml")
public interface RunService {

	@POST
	@Path("/addRunRecord")
	@Consumes("application/xml")
	public Response addRunRecord(RunRecord runRecord);

	@GET
	@Path("/getRunRecord/{userID}/{time}")
	@Consumes("application/xml")
	public RunRecordList getRunRecord(@PathParam("userID")String userID, @PathParam("time")String time);

}
