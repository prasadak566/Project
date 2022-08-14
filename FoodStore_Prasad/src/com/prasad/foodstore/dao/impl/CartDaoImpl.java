package com.prasad.foodstore.dao.impl;

import java.util.ArrayList;

import java.util.List;
import java.sql.*;
import com.prasad.foodstore.dao.CartDao;
import com.prasad.foodstore.pojo.Cart;
import com.prasad.foodstore.pojo.Food;
import com.prasad.foodstore.pojo.User;
import com.prasad.foodstore.utility.DBConnection;

public class CartDaoImpl implements com.prasad.foodstore.dao.CartDao{

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String sqlQuery;
	
	FoodDaoImpl fooddao = new FoodDaoImpl();
	@Override
	public boolean addToCart(Cart cart) {
		con = DBConnection.openConnection();
		sqlQuery = "insert into cart (foodId,itemQuantity,userId) values(?,?,?)";
		try
		{
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1,cart.getFoodId());
			ps.setInt(2, cart.getItemQuantity());
			ps.setInt(3, cart.getUserId());
			
			int i = ps.executeUpdate();
			
			if(i>0)
			{
				System.out.println(i+" Inserted...");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		
		return false;
	
	}

	@Override
	public boolean removeItem(int itemId) {
		con = DBConnection.openConnection();
		sqlQuery = "delete from cart where itemid=?";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, itemId);
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println(i+"Row removed...");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		
		return false;
	}

	@Override
	public boolean clearCart(int userId) {
		con = DBConnection.openConnection();
		sqlQuery = "delete from cart where userid=?";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println("Cleard all data from cart...");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			DBConnection.closeConnection();
		}
		
		return false;
	}

	@Override
	public List<Cart> viewCart(int userId) {
		List<Cart> cartlist = new ArrayList<Cart>();
		
		con = DBConnection.openConnection();
		sqlQuery = "select * from cart where userId=?";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Food food = fooddao.getById(rs.getInt("foodId"));
				
				Cart cart = new Cart();
				cart.setItemId(rs.getInt("itemId"));
				cart.setFoodId(rs.getInt("foodId"));
				cart.setFood(food);
				cart.setItemQuantity(rs.getInt("itemQuantity"));
				cart.setUserId(rs.getInt("userId"));
				
				cartlist.add(cart);
			}
			return cartlist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();
		}
		return null;
	}

	
	public static void main(String[] args) {
		//Add to cart testing
		UserDaoImpl userdao = new UserDaoImpl();
		CartDaoImpl cartdao = new CartDaoImpl();
		
		// USER LOGIN
		User u = userdao.login("jay@gamil.com", "1234");
		
		/*Cart c = new Cart(4, 3, u.getUserId());
		cartdao.addToCart(c);
		*/
		
		//View cart testing 
	/*	List<Cart> cartlist = cartdao.viewCart(u.getUserId());
		
		for (Cart item:cartlist) {
			System.out.println(item);
		}
		*/
		// testing for remove
	//	cartdao.removeItem(1);
		
		//Testing for clearcart 
		cartdao.clearCart(3);
	}
}
