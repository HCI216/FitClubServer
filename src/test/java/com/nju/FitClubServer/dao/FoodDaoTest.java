package com.nju.FitClubServer.dao;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.impl.FoodDaoImpl;
import com.nju.FitClubServer.model.Food;

public class FoodDaoTest {

	private static FoodDao foodDao;

	static {
		foodDao = new FoodDaoImpl();
	}

	public void printList(ArrayList<Food> foodList) {
		for (int i = 0; i < foodList.size(); i++) {
			System.out.println("id = " + foodList.get(i).getFoodID());
			System.out.println("name = " + foodList.get(i).getFoodName());
			System.out.println("smallCategory = "
					+ foodList.get(i).getSmallCategory());
			System.out.println("bigCategory = "
					+ foodList.get(i).getBigCategory());
			System.out.println("--------------------------");
		}
	}

	public void printFood(Food food) {
		System.out.println("id = " + food.getFoodID());
		System.out.println("name = " + food.getFoodName());
		System.out.println("smallCategory = " + food.getSmallCategory());
		System.out.println("bigCategory = " + food.getBigCategory());
		System.out.println("--------------------------");
	}

	public void testGetFoodBySmallCategoryAndBigCategory(String smallCategory,
			String bigCategory) throws Exception {
		ArrayList<Food> foodList = foodDao
				.getFoodBySmallCategoryAndBigCategory(smallCategory,
						bigCategory);
		printList(foodList);
	}

	public void testGetSmallCategoryByBigCategory(String bigCategory)
			throws Exception {
		ArrayList<String> caList = foodDao
				.getSmallCategoryByBigCategory(bigCategory);
		for (int i = 0; i < caList.size(); i++) {
			System.out.println(caList.get(i));
		}
	}

	public void testGetFoodByNameAndSmallCategoryAndBigCategory(
			String foodName, String smallCategory, String bigCategory)
			throws Exception {
		Food food = foodDao.getFoodByNameAndSmallCategoryAndBigCategory(
				foodName, smallCategory, bigCategory);
		printFood(food);
	}

	public void testGetFoodByFoodID(String foodID) throws Exception {

		Food food = foodDao.getFoodByFoodID(foodID);
		printFood(food);
	}

	public void testGetFoodByName(String foodName) throws Exception {
		ArrayList<Food> foodList = foodDao.getFoodByName(foodName);
		printList(foodList);
	}

	public static void main(String[] args)  throws Exception{
		FoodDaoTest test = new FoodDaoTest();
//		test.testGetFoodByFoodID("12");
//		test.testGetFoodByName("鸡蛋");
//		test.testGetFoodByNameAndSmallCategoryAndBigCategory("土豆沙拉加鸡蛋", "土豆沙拉[]沙拉类", "traditional");
//		test.testGetFoodBySmallCategoryAndBigCategory("土豆沙拉[]沙拉类", "traditional");
	}

}
