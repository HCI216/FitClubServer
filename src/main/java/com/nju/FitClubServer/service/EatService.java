package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.EatRecord;
import com.nju.FitClubServer.model.EatRecordList;

@Path("/eatservice")
@Produces("application/xml")
public interface EatService {
	
	@POST
	@Path("/addEatRecord")
	@Consumes("application/xml")
	public boolean addEatRecord(EatRecord eatRecord);
	
	@GET
	@Path("/getEatRecord/{userID}/{time}")
	public EatRecordList getEatRecord(@PathParam("userID")String userID,@PathParam("time")String time);
	
}
