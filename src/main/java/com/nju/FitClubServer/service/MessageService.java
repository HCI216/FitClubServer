package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.nju.FitClubServer.model.GetRunMateRecord;
import com.nju.FitClubServer.model.GetRunMateRecordList;
import com.nju.FitClubServer.model.InviteMessage;
import com.nju.FitClubServer.model.InviteMessageList;

@Path("/messageservice")
@Produces("application/xml")
public interface MessageService {

	@POST
	@Path("/sendGetRunMateMessage")
	@Consumes("application/xml")
	public Response sendGetRunMateMessage(GetRunMateRecord getRunMateRecord);

	@POST
	@Path("/sendInviteMessage")
	@Consumes("application/xml")
	public Response sendInviteMessage(InviteMessage inviteMessage);
	
	@GET
	@Path("/getGetRunMateRecord")
	@Consumes("application/xml")
	public GetRunMateRecordList getGetRunMateRecord();
	
	@GET
	@Path("/getReceiveMessage/{userID}")
	@Consumes("application/xml")
	public InviteMessageList getReceiveMessage(@PathParam("userID")String userID);
	

	@GET
	@Path("/getSendMessage/{userID}")
	@Consumes("application/xml")
	public InviteMessageList getSendMessage(@PathParam("userID")String userID);
	
	@PUT
	@Path("/readMessage/{inviteMessageID}/{newState}")
	@Consumes("application/xml")
	public Response readMessage(@PathParam("inviteMessageID")String inviteMessageID,@PathParam("newState")String newState);
	
}

