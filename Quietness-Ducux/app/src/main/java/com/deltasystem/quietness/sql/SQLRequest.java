package com.deltasystem.quietness.sql;


import android.os.StrictMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLRequest {
	private String host = "sql10.freesqldatabase.com";
	private String name = "sql10449029";
	private String pass="biAiMlF4ue";
	
	private String jdbcUrl = "jdbc:mysql://"+host+":3306/"+name; //DataBase DIR
	
	public void insert_user(String a) { //Insert users
		String usersql = String.format("INSERT INTO clients VALUES(%s)",a); //SQL sentence
		Connection connection = null; //Connection initialize
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,name,pass); //Connect to DB
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


	public void update_user(String n_name,String n_username,String n_email, String n_gender,String o_user,String o_passwd ) { //update users, correct that they are only for existing users (not showing "LOGIC" error).
		
		String updateSql =String.format("UPDATE clients SET name = \"%s\",username=\"%s\",email=\"%s\",gender='%s' WHERE email = \"%s\" AND passwd = \"%s\"; ",n_name,n_username,n_email,n_gender,o_user,o_passwd);//SQL sentence
		Connection connection = null;//Connection initialize
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,name, pass); //Connect to DB
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(updateSql); // Execute SQL sentence
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

	public String[] get_info(String _table, String _user, String _passwd) { //Show users in DB
		String info[] = new String[5];
		String sql = String.format("SELECT * FROM %s WHERE (email=\"%s\" AND passwd=\"%s\") ",_table,_user,_passwd); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			//configuring the database
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,name, pass); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) { //extracts the information from the database

				info[0] = result.getString("username");
				info[1] = result.getString("name");
				info[2] = result.getString("email");
				info[3] = String.valueOf(result.getString("gender"));
				info[4] = result.getString("quiz");
			}
			return info;

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
		return null;
	}

	public String get_hist(){
		String info=null;
		String sql = String.format("SELECT * FROM hist_txt ORDER BY RAND() LIMIT 1"); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			//configuring the database
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,name,pass); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) { //extracts the information from the database

				info=result.getString("hist");
			}
			return info;

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
		return null;
	}
}
