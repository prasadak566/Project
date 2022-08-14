package com.prasad.foodstore.pojo;

public class Cart {

	private int itemId; // Primary key
	private int foodId; // Foreign key (its the reference from the food table)
	private Food food; // all data of food
	private int itemQuantity = 1;
	private int userId;

	public Cart() {
		super();
	}

	public Cart(int foodId, int itemQuantity, int userId) {
		super();
		this.foodId = foodId;
		this.itemQuantity = itemQuantity;
		this.userId = userId;
	}
	
	public Cart(int itemId, int foodId, Food food, int itemQuantity, int userId) {
		super();
		this.itemId = itemId;
		this.foodId = foodId;
		this.food = food;
		this.itemQuantity = itemQuantity;
		this.userId = userId;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Cart [itemId=" + itemId + ", foodId=" + foodId + ", food=" + food + ", itemQuantity=" + itemQuantity
				+ ", userId=" + userId + "]";
	}
	
	
	
	
}
