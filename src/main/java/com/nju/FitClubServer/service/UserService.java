package com.nju.FitClubServer.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nju.FitClubServer.model.ImageHelperModel;
import com.nju.FitClubServer.model.User;

@Path("/userservice")
@Produces("application/xml")
public interface UserService {

	@PUT
	@Path("/login/{username}")
	@Consumes("application/xml")
	public Response login(@PathParam("username") String userName, User user);

	@POST
	@Path("/register")
	@Consumes("application/xml")
	public Response register(User user);
	
	@PUT
	@Path("/logout/{username}")
	@Consumes("application/xml")
	public Response logout(@PathParam("username") String userName,User userLogout);

	@POST
	@Path("/uploadImage/{username}")
	@Consumes("application/xml")
	public void uploadImage(@PathParam("username") String username,
			ImageHelperModel imageHelper);

	@GET
	@Path("/downloadImage/{username}/{position}")
	@Consumes("application/xml")
	public ImageHelperModel downloadImage(@PathParam("username") String username,
			@PathParam("position")long position);

}
