package com.prasad.foodstore.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static Connection con =null;
	
	public static Connection openConnection() 
	{
		if(con==null)
		{
			try 
			{
				//1.Register the mysql driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//2. Establish the Connection
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodstore","root","Root@1234");
				System.out.println("Connected.");
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
		return con; 
	}
	//above method is to establish connection between javaProject and Database
	// and return object of connection
	
	public static void closeConnection()
	{
		if(con!=null)
		{
			try 
			{
				//7. Close the connection
				con.close();
				con=null; // you can add, update, select multiple data 
				//System.out.println("Connection is Closed.");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		DBConnection.openConnection();
		
		DBConnection.closeConnection();
	}
}


