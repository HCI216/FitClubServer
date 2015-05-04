//package com.nju.FitClubServer;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//import com.nju.FitClubServer.model.Food;
//
//public class DatabaseTest {
//
//	String url = "jdbc:mysql://localhost:3306/fitclub";
//
//	public Connection getCon() {
//		Connection con = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			con = DriverManager.getConnection(url, "root", "nzbbzlks");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return con;
//	}
//
//	public int getNum() throws Exception {
//		int result = 0;
//		Connection con = getCon();
//		String query = "select count(*) from food";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//		while (set.next())
//			result = set.getInt(1);
//		// System.out.println(result);
//		return result;
//	}
//
//	public ArrayList<Food> getMarketFood() throws Exception {
//		ArrayList<Food> foodList = new ArrayList<Food>();
//
//		Connection con = getCon();
//
//		String query = "select * from marketFood";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//
//		int tmp = getNum();
//
//		while (set.next()) {
//			Food food = new Food();
//			food.setFoodID(Integer.parseInt(set.getString("foodID")) + tmp + "");
//			food.setFoodName(set.getString("foodName"));
//			food.setFoodAmount(set.getString("foodAmonut"));
//			food.setCalorie(set.getDouble("calorie"));
//			food.setFat(set.getDouble("fat"));
//			food.setCarbobydrate(set.getDouble("carbohydrate"));
//			food.setProtein(set.getDouble("protein"));
//			food.setSmallCategory(set.getString("marketName"));
//			food.setBigCategory("market");
//			foodList.add(food);
//		}
//
//		return foodList;
//	}
//
//	public ArrayList<Food> getRsFood() throws Exception {
//		ArrayList<Food> foodList = new ArrayList<Food>();
//
//		Connection con = getCon();
//
//		String query = "select * from restaurantFood";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//
//		int tmp = getNum();
//
//		while (set.next()) {
//			Food food = new Food();
//			food.setFoodID(Integer.parseInt(set.getString("foodID")) + tmp + "");
//			food.setFoodName(set.getString("foodName"));
//			food.setFoodAmount(set.getString("foodAmonut"));
//			food.setCalorie(set.getDouble("calorie"));
//			food.setFat(set.getDouble("fat"));
//			food.setCarbobydrate(set.getDouble("carbohydrate"));
//			food.setProtein(set.getDouble("protein"));
//			food.setSmallCategory(set.getString("restaurantName"));
//			food.setBigCategory("restaurant");
//			foodList.add(food);
//		}
//
//		return foodList;
//	}
//
//	public void updateFood() throws Exception {
//		ArrayList<Food> foodList = new ArrayList<Food>();
//
//		Connection con = getCon();
//
//		String query = "select * from food";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//
//		while (set.next()) {
//			Food food = new Food();
//			if (Integer.parseInt(set.getString("foodID")) > 9465)
//				continue;
//			food.setFoodID(set.getString("foodID"));
//			food.setFoodName(set.getString("foodName"));
//			food.setFoodAmount(set.getString("foodAmount"));
//			food.setCalorie(set.getDouble("calorie"));
//			food.setFat(set.getDouble("fat"));
//			food.setCarbobydrate(set.getDouble("carbohydrate"));
//			food.setProtein(set.getDouble("protein"));
//			food.setSmallCategory(set.getString("smallCategory"));
//			food.setBigCategory(set.getString("bigCategory"));
//			foodList.add(food);
//		}
//		addFoods(foodList);
//	}
//
//	public ArrayList<Food> getBrandsFood() throws Exception {
//		ArrayList<Food> foodList = new ArrayList<Food>();
//
//		Connection con = getCon();
//
//		String query = "select * from brandsFood";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//
//		int tmp = getNum();
//
//		while (set.next()) {
//			Food food = new Food();
//			food.setFoodID(Integer.parseInt(set.getString("foodID")) + tmp + "");
//			food.setFoodName(set.getString("foodName"));
//			food.setFoodAmount(set.getString("foodAmonut"));
//			food.setCalorie(set.getDouble("calorie"));
//			food.setFat(set.getDouble("fat"));
//			food.setCarbobydrate(set.getDouble("carbohydrate"));
//			food.setProtein(set.getDouble("protein"));
//			food.setSmallCategory(set.getString("brandsName"));
//			food.setBigCategory("brand");
//			foodList.add(food);
//		}
//
//		return foodList;
//	}
//
//	public ArrayList<Food> getTraditionalFood() throws Exception {
//		ArrayList<Food> foodList = new ArrayList<Food>();
//
//		Connection con = getCon();
//
//		String query = "select * from traditionalFood";
//		PreparedStatement stmt = con.prepareStatement(query);
//		ResultSet set = stmt.executeQuery();
//
//		while (set.next()) {
//			Food food = new Food();
//			food.setFoodID(set.getString("foodID"));
//			food.setFoodName(set.getString("foodName"));
//			food.setFoodAmount(set.getString("foodAmonut"));
//			food.setCalorie(set.getDouble("calorie"));
//			food.setFat(set.getDouble("fat"));
//			food.setCarbobydrate(set.getDouble("carbohydrate"));
//			food.setProtein(set.getDouble("protein"));
//			food.setSmallCategory(set.getString("bigCategory") + "[]"
//					+ set.getString("smallCategory"));
//			food.setBigCategory("traditional");
//			foodList.add(food);
//		}
//
//		return foodList;
//	}
//
//	public void addFoods(ArrayList<Food> foodList) throws Exception {
//		Connection con = getCon();
//
//		for (int i = 0; i < foodList.size(); i++) {
//			Food food = foodList.get(i);
//			String query = "insert into foods values(?,?,?,?,?,?,?,?,?)";
//			PreparedStatement stmt = con.prepareStatement(query);
//			stmt.setString(1, food.getFoodID());
//			stmt.setString(2, food.getFoodName());
//			stmt.setString(3, food.getFoodAmount());
//			stmt.setDouble(4, food.getCalorie());
//			stmt.setDouble(5, food.getFat());
//			stmt.setDouble(6, food.getCarbobydrate());
//			stmt.setDouble(7, food.getProtein());
//			stmt.setString(8, food.getSmallCategory());
//			stmt.setString(9, food.getBigCategory());
//			stmt.execute();
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		DatabaseTest test = new DatabaseTest();
//
//		test.updateFood();
//		// ArrayList<Food> foodList = test.getRsFood();
//		// test.addFoods(foodList);
//		// ArrayList<Food> foodList = test.getMarketFood();
//		// test.addFoods(foodList);
//		// ArrayList<Food> foodList = test.getTraditionalFood();
//		// test.addTraditionalFoods(foodList);
//	}
//
//}
