package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.model.Food;

public interface FoodDao {

	public ArrayList<Food> getFoodBySmallCategoryAndBigCategory(
			String smallCategory,String bigCategory) throws Exception;

	public ArrayList<String> getSmallCategoryByBigCategory(String bigCategory)
			throws Exception;

	public Food getFoodByNameAndSmallCategoryAndBigCategory(String foodName,
			String smallCategory, String bigCategory) throws Exception;

	public Food getFoodByFoodID(String foodID) throws Exception;

	public ArrayList<Food> getFoodByName(String foodName) throws Exception;

}
