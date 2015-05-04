package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.Food;

@Path("/foodservice")
@Produces("application/xml")
public interface FoodService {

	@GET
	@Path("/getSmallCategoryByBigCategory/{bigCategory}")
	@Consumes("application/xml")
	public ArrayList<String> getSmallCategoryByBigCategory(@PathParam("bigCategory")String bigCategory);

	@GET
	@Path("/getTraditionalCategoryFromSmallCategory/{smallCategory}")
	@Consumes("application/xml")
	public ArrayList<String> getTraditionalCategoryFromSmallCategory(
			@PathParam("smallCategory")String smallCategory);

	@GET
	@Path("/getFoodByBigCategoryAndSmallCategory/{bigCategory}/{smallCategory}")
	@Consumes("application/xml")
	public ArrayList<Food> getFoodByBigCategoryAndSmallCategory(
			String bigCategory, String smallCategory);

	@GET
	@Path("/getTraditionalFoodBySmallCategory/{smallCategory_part1}/{smallCategory_part2}")
	@Consumes("application/xml")
	public ArrayList<Food> getTraditionalFoodBySmallCategory(
			String smallCategory_part1, String smallCategory_part2);
	
	@GET
	@Path("/searchFood/{content}")
	@Consumes("application/xml")
	public ArrayList<Food> searchFoodByContent(@PathParam("content") String content);
}
