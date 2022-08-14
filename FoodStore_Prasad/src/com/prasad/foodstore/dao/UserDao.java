package com.prasad.foodstore.dao;

import com.prasad.foodstore.pojo.User;

public interface UserDao {

	boolean register(User user);
	User login(String userEmail, String userPassword);
	// if Email and Password is match then return user data otherwise null;
	void updateProfile(int userId, User user);
	boolean deleteProfile(int userId);
}
