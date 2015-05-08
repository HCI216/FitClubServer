package com.nju.FitClubServer.service;

import java.util.ArrayList;

import javax.ws.rs.PathParam;

import com.nju.FitClubServer.model.Food;
import com.nju.FitClubServer.service.impl.FoodServiceImpl;

public class FoodServiceTest {

	private FoodService service = new FoodServiceImpl();

	public void getSmallCategoryByBigCategory() {
		ArrayList<String> strList = service
				.getSmallCategoryByBigCategory("brand").getCategoryList();

		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}

	}

	public void getTraditionalCategoryFromSmallCategory() {
		ArrayList<String> strList = service
				.getTraditionalCategoryFromSmallCategory("小吃类").getCategoryList();
		for (int i = 0; i < strList.size(); i++) {
			System.out.println(strList.get(i));
		}
	}

	public void getFoodByBigCategoryAndSmallCategory() {
		ArrayList<Food> foodList = service
				.getFoodByBigCategoryAndSmallCategory("brand", "达利园").getFoods();

		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			System.out.print(food.getFoodName() + " : ");
			System.out.println(food.getBigCategory() + " /"
					+ food.getSmallCategory());
		}

	}

	public void getTraditionalFoodBySmallCategory() {
		ArrayList<Food> foodList = service.getTraditionalFoodBySmallCategory("蛋类", "蛋黄").getFoods();
		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			System.out.print(food.getFoodName() + " : ");
			System.out.println(food.getBigCategory() + " /"
					+ food.getSmallCategory());
		}
	}
	
	public  void searchFoodByContent(){
		ArrayList<Food> foodList = service.searchFoodByContent("鸡蛋").getFoods();
		for (int i = 0; i < foodList.size(); i++) {
			Food food = foodList.get(i);
			System.out.print(food.getFoodName() + " : ");
			System.out.println(food.getBigCategory() + " /"
					+ food.getSmallCategory());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoodServiceTest test = new FoodServiceTest();
		test.getSmallCategoryByBigCategory();

		System.out.println("-----------------------------");

		test.getTraditionalCategoryFromSmallCategory();

		System.out.println("-----------------------------");

		test.getFoodByBigCategoryAndSmallCategory();
		
		System.out.println("-----------------------------");
		test.getTraditionalFoodBySmallCategory();
		System.out.println("-----------------------------");
		test.searchFoodByContent();
	}

}
