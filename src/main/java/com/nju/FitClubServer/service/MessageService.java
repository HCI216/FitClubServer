package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.GetRunMateRecord;
import com.nju.FitClubServer.model.InviteMessage;

@Path("/messageservice")
@Produces("application/xml")
public interface MessageService {

	@POST
	@Path("/sendGetRunMateMessage")
	@Consumes("application/xml")
	public boolean sendGetRunMateMessage(GetRunMateRecord getRunMateRecord);

	@POST
	@Path("/sendInviteMessage")
	@Consumes("application/xml")
	public boolean sendInviteMessage(InviteMessage inviteMessage);
	
	@GET
	@Path("/getGetRunMateRecord")
	@Consumes("application/xml")
	public ArrayList<GetRunMateRecord> getGetRunMateRecord();
	
	@GET
	@Path("/getReceiveMessage/{userID}")
	@Consumes("application/xml")
	public ArrayList<InviteMessage> getReceiveMessage(@PathParam("userID")String userID);
	

	@GET
	@Path("/getSendMessage/{userID}")
	@Consumes("application/xml")
	public ArrayList<InviteMessage> getSendMessage(@PathParam("userID")String userID);
	
	@PUT
	@Path("/readMessage/{inviteMessageID}/{newState}")
	@Consumes("application/xml")
	public boolean readMessage(@PathParam("inviteMessageID")String inviteMessageID,@PathParam("newState")String newState);
	
}

