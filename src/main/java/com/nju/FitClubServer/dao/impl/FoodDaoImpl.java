package com.nju.FitClubServer.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nju.FitClubServer.dao.FoodDao;
import com.nju.FitClubServer.model.Food;

public class FoodDaoImpl implements FoodDao {

	String url = "jdbc:mysql://localhost:3306/fitclub";

	public Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, "root", "nzbbzlks");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public ArrayList<Food> getFoodBySmallCategoryAndBigCategory(
			String smallCategory, String bigCategory) throws Exception {

		ArrayList<Food> foodList = new ArrayList<Food>();

		String query = "select * from foods where smallCategory=? and bigCategory=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, smallCategory);
		stmt.setString(2, bigCategory);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			Food food = new Food();
			food.setFoodID(set.getString("foodID"));
			food.setFoodName(set.getString("foodName"));
			food.setFoodAmount(set.getString("foodAmount"));
			food.setCalorie(set.getDouble("calorie"));
			food.setFat(set.getDouble("fat"));
			food.setCarbobydrate(set.getDouble("carbohydrate"));
			food.setProtein(set.getDouble("protein"));
			food.setSmallCategory(smallCategory);
			food.setBigCategory(bigCategory);
			foodList.add(food);
		}
		return foodList;
	}

	public ArrayList<String> getSmallCategoryByBigCategory(String bigCategory)
			throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> categoryList = new ArrayList<String>();

		String query = "select distinct smallCategory from foods where bigCategory=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, bigCategory);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			categoryList.add(set.getString("smallCategory"));
		}
		return categoryList;
	}

	public Food getFoodByNameAndSmallCategoryAndBigCategory(String foodName,
			String smallCategory, String bigCategory) throws Exception {
		// TODO Auto-generated method stub

		Food food = new Food();
		String query = "select * from foods where foodName=? and smallCategory=? and bigCategory=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, foodName);
		stmt.setString(2, smallCategory);
		stmt.setString(3, bigCategory);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			food.setFoodID(set.getString("foodID"));
			food.setFoodName(foodName);
			food.setFoodAmount(set.getString("foodAmount"));
			food.setCalorie(set.getDouble("calorie"));
			food.setFat(set.getDouble("fat"));
			food.setCarbobydrate(set.getDouble("carbohydrate"));
			food.setProtein(set.getDouble("protein"));
			food.setSmallCategory(smallCategory);
			food.setBigCategory(bigCategory);
		}
		return food;
	}

	public Food getFoodByFoodID(String foodID) throws Exception {
		// TODO Auto-generated method stub
		Food food = new Food();
		String query = "select * from foods where foodID=?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, foodID);
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			food.setFoodID(set.getString("foodID"));
			food.setFoodName(set.getString("foodName"));
			food.setFoodAmount(set.getString("foodAmount"));
			food.setCalorie(set.getDouble("calorie"));
			food.setFat(set.getDouble("fat"));
			food.setCarbobydrate(set.getDouble("carbohydrate"));
			food.setProtein(set.getDouble("protein"));
			food.setSmallCategory(set.getString("smallCategory"));
			food.setBigCategory(set.getString("bigCategory"));
		}
		return food;
	}

	public ArrayList<Food> getFoodByName(String foodName) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Food> foodList = new ArrayList<Food>();

		String query = "select * from foods where foodName like ?";
		Connection con = getCon();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, "%"+foodName+"%");
		ResultSet set = stmt.executeQuery();
		while (set.next()) {
			Food food = new Food();
			food.setFoodID(set.getString("foodID"));
			food.setFoodName(set.getString("foodName"));
			food.setFoodAmount(set.getString("foodAmount"));
			food.setCalorie(set.getDouble("calorie"));
			food.setFat(set.getDouble("fat"));
			food.setCarbobydrate(set.getDouble("carbohydrate"));
			food.setProtein(set.getDouble("protein"));
			food.setSmallCategory(set.getString("smallCategory"));
			food.setBigCategory(set.getString("bigCategory"));
			foodList.add(food);
		}
		return foodList;
	}
}
