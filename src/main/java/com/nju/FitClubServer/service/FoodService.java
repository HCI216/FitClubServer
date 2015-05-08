package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.nju.FitClubServer.model.Food;
import com.nju.FitClubServer.model.FoodCategoryList;
import com.nju.FitClubServer.model.FoodList;

@Path("/foodservice")
@Produces("application/xml")
public interface FoodService {

	@GET
	@Path("/getSmallCategoryByBigCategory/{bigCategory}")
	@Consumes("application/xml")
	public FoodCategoryList getSmallCategoryByBigCategory(
			@PathParam("bigCategory") String bigCategory);

	@GET
	@Path("/getTraditionalCategoryFromSmallCategory/{smallCategory}")
	@Consumes("application/xml")
	public FoodCategoryList getTraditionalCategoryFromSmallCategory(
			@PathParam("smallCategory") String smallCategory);

	@GET
	@Path("/getFoodByBigCategoryAndSmallCategory/{bigCategory}/{smallCategory}")
	@Consumes("application/xml")
	public FoodList getFoodByBigCategoryAndSmallCategory(
			@PathParam("bigCategory") String bigCategory,
			@PathParam("smallCategory") String smallCategory);

	@GET
	@Path("/getTraditionalFoodBySmallCategory/{smallCategory_part1}/{smallCategory_part2}")
	@Consumes("application/xml")
	public FoodList getTraditionalFoodBySmallCategory(
			@PathParam("smallCategory_part1") String smallCategory_part1,
			@PathParam("smallCategory_part2") String smallCategory_part2);

	@GET
	@Path("/searchFood/{content}")
	@Consumes("application/xml")
	public FoodList searchFoodByContent(@PathParam("content") String content);
}
