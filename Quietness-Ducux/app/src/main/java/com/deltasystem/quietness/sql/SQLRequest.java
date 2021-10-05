package com.deltasystem.quietness.sql;
import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLRequest {
	
	String jdbcUrl = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3442286"; //DataBase DIR
	
	public void insertUser(String a) { //Insert users
		String usersql = String.format("INSERT INTO clients VALUES(%s)",a); //SQL sentence
		Connection connection = null; //Connection initialize
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
			Statement statement = connection.createStatement(); 
			int result = statement.executeUpdate(usersql);// Execute SQL sentence
			if(result>0) {
				System.out.println("ROW CREATED");
			}else {
				System.out.println("NO CREATED");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); //Report ERROR
		} finally {
			  if (connection != null) { //Close connection
				    try {
				      connection.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
			}

	}
	
	private void RemoveRaw(int userID) { //Remove users, no used
		String removeSql =String.format("DELETE FROM users WHERE IDUser = %d; DELETE FROM Encuestas WHERE id=%d;",userID,userID); //SQL sentence
		Connection connection = null; //Connection initialize
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(removeSql);// Execute SQL sentence
			if(result>0) {
				System.out.println("ROW UPDATE");
			}else {
				System.out.println("NO ROW UPDATE");
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); //Report ERROR
		}finally {
			  if (connection != null) {//Close connection
				    try {
				      connection.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
			}
	}
	
	public void updateUser(String a, int userID) { //update users, correct that they are only for existing users (not showing "LOGIC" error).
		
		String updateSql =String.format("UPDATE users SET %s WHERE IDUser = %d;",a,userID);//SQL sentence
		Connection connection = null;//Connection initialize
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(updateSql);// Execute SQL sentence
			
			if(result>0) {
				System.out.println("ROW UPDATE");
			}else {
				System.out.println("NO ROW UPDATE");
			}
		}catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();//Report ERROR
		}
		finally {
			  if (connection != null) {
				    try {
				      connection.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace(); //Close connection
				    }
				  }
			}
	}
	 
	
	public void Show(String table) { //Show DB, does not respect SOLID `IF`
		String sql = String.format("SELECT * FROM %s",table); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			if(table=="users") { //users table
				while (result.next()) {
					Integer id = result.getInt("IDUser");
					String name= result.getString("name");
					String cargo= result.getString("cargo");
					String email= result.getString("email");
					String passwd= result.getString("passwd");
					
					System.out.println(id+" | "+name+" | "+cargo+" | "+email+" | "+passwd);
				}
			}else {//Encuestas Table
				while (result.next()) { 
					Integer id = result.getInt("id");
					String one= result.getString("oneQ");
					String two= result.getString("twoQ");
					String three= result.getString("threeQ");
					String four= result.getString("fourQ");
					String five= result.getString("fiveQ");
					
					System.out.println(id+" | "+one+" | "+two+" | "+three+" | "+four+" | "+five);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();//Report ERROR
		}
		finally {
			  if (connection != null) {
				    try {
				      connection.close(); // closes Connection
				    } catch (SQLException e) {
				      e.printStackTrace(); //Report ERROR
				    }
				  }
			}
	}
	 
}
