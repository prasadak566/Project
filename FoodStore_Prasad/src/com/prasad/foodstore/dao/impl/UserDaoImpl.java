package com.prasad.foodstore.dao.impl;

import java.sql.*;

import com.prasad.foodstore.dao.UserDao;
import com.prasad.foodstore.pojo.User;
import com.prasad.foodstore.utility.DBConnection;

public class UserDaoImpl implements UserDao{

	Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sqlQuery;
    
	@Override
	public boolean register(User user) {
		con = DBConnection.openConnection();
		
		sqlQuery = "insert into users(userName, userEmail, userPassword, userAddress, userRole) values (?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserAddress());
			ps.setString(5, user.getUserRole());
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println(i+"Row inserted...");
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();
		}
		return false;
	}

	@Override
	public User login(String userEmail, String userPassword) {
		con = DBConnection.openConnection();
		sqlQuery = "select *  from users where userEmail=? and userPassword=?";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1,  userEmail);
			ps.setString(2, userPassword);
			
			rs= ps.executeQuery();
			
			if(rs.next()) {
				User user = new User (rs.getInt("userId"),
						rs.getString("userName"),
						rs.getString("userEmail"),
						rs.getString("userPassword"),
						rs.getString("userAddress"),
						rs.getString("userrole"));
				return user;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();
		}
		return null;
	} 
	

	@Override
	public void updateProfile(int userId, User user) {
		con = DBConnection.openConnection();
		sqlQuery = "update users set userName=?, userEmail=?, userPassword=?, userAddress=? where userId=?";
		
		try {
			ps = con.prepareStatement(sqlQuery);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserAddress());
			ps.setInt(5, userId);
			
			int i = ps.executeUpdate();
			if(i>0) {
				System.out.println(i+" Row Updated...");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();
		}
	}

	@Override
	public boolean deleteProfile(int userId) {
		con = DBConnection.openConnection();
		sqlQuery = "delete from users where userId=?";
		
		try {
			ps= con.prepareStatement(sqlQuery);
			ps.setInt(1, userId);
			
			int i = ps.executeUpdate();
			if (i>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();
		}
		return false;
	}

	public static void main(String[] args) {
		UserDaoImpl userdao = new UserDaoImpl();
		
	/*  Regestration Testing
	 	User u = new User("Raj Patel", "raj1@gmail.com", "123", "Mumbai", "Admin");
	    userdao.register(u);
	    
	    User u1 = new User("Yesh Yadav", "yyesh1@gmail.com", "123", "Mumbai", "Cuostmer");
	    userdao.register(u1);
	    */
		
		// Login Testing 
	/*	User u2 = userdao.login("raj@gmail.com", "123");
		System.out.println(u2);
		
		User u3 = userdao.login("jay@gmail.com", "1234");
		System.out.println(u3);
		*/
		
		// update profile testing
		
	/*	User u5 = new User("Jayesh Yadav", "jay@gamil.com", "1234", "Mumbai", "Customer");
		userdao.updateProfile(u3.getUserId(), u5);
		
		u3 = userdao.login("jay@gmail.com", "1234");
		System.out.println(u3);
		*/
		
		boolean flag = userdao.deleteProfile(0);
		System.out.println(flag);
		
		flag = userdao.deleteProfile(1);
		System.out.println(flag);
	}
}
