package com.prasad.foodstore.dao;


import java.util.List;

import com.prasad.foodstore.pojo.Food;

public interface FoodDao {
	boolean add(Food food);
	/*
	 * Here boolean to signal that respected Food is add or not
	 * if added then return true otherwise return false.
	 */
		boolean update (int foodId, Food food);
		boolean delete (int foodId);
		List<Food> all();
		Food getById(int foodId);
}
