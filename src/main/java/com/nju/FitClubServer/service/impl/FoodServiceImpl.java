package com.nju.FitClubServer.service.impl;

import java.util.ArrayList;

import com.nju.FitClubServer.dao.FoodDao;
import com.nju.FitClubServer.dao.impl.FoodDaoImpl;
import com.nju.FitClubServer.model.Food;
import com.nju.FitClubServer.service.FoodService;

public class FoodServiceImpl implements FoodService {

	private FoodDao dao = new FoodDaoImpl();

	public ArrayList<String> getSmallCategoryByBigCategory(String bigCategory) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			if (!bigCategory.equals("traditional"))
				list = dao.getSmallCategoryByBigCategory(bigCategory);
			else {
				ArrayList<String> pp = dao
						.getSmallCategoryByBigCategory(bigCategory);
				for (int i = 0; i < pp.size(); i++) {
					if (!list.contains(getFirstStr(pp.get(i))))
						list.add(getFirstStr(pp.get(i)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getFirstStr(String input) {
		String result = "";
		char[] rList = input.toCharArray();
		boolean add = false;
		for (int i = 0; i < rList.length; i++) {
			if (add)
				result += rList[i];
			if (rList[i] == ']')
				add = true;
		}
		return result;
	}

	private String getSecondStr(String input) {
		String result = "";
		char[] rList = input.toCharArray();
		for (int i = 0; i < rList.length; i++) {
			if (rList[i] == '[')
				break;
			result += rList[i];
		}
		return result;
	}

	public ArrayList<String> getTraditionalCategoryFromSmallCategory(
			String smallCategory) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			ArrayList<String> tmp = dao
					.getSmallCategoryByBigCategory("traditional");
			for (int i = 0; i < tmp.size(); i++) {
				String firstPart = getFirstStr(tmp.get(i));
				String secPart = getSecondStr(tmp.get(i));
				if (firstPart.equals(smallCategory)
						&& (!list.contains(secPart))) {
					list.add(secPart);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Food> getFoodByBigCategoryAndSmallCategory(
			String bigCategory, String smallCategory) {
		// TODO Auto-generated method stub

		ArrayList<Food> foodList = new ArrayList<Food>();
		try {
			foodList = dao.getFoodBySmallCategoryAndBigCategory(smallCategory,
					bigCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foodList;
	}

	public ArrayList<Food> getTraditionalFoodBySmallCategory(
			String smallCategory_part1, String smallCategory_part2) {
		// TODO Auto-generated method stub
		ArrayList<Food> foodList = new ArrayList<Food>();
		try {
			foodList = dao.getFoodBySmallCategoryAndBigCategory(
					smallCategory_part2 + "[]" + smallCategory_part1,
					"traditional");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foodList;
	}

	public ArrayList<Food> searchFoodByContent(String content) {
		// TODO Auto-generated method stub
		ArrayList<Food> foodList = new ArrayList<Food>();
		try {
			foodList = dao.getFoodByName(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return foodList;

	}

}