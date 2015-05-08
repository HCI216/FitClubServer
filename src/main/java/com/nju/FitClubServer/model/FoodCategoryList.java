package com.nju.FitClubServer.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "FoodCategoryList")
public class FoodCategoryList {
	private ArrayList<String> categoryList;

	public ArrayList<String> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(ArrayList<String> categoryList) {
		this.categoryList = categoryList;
	}

}
