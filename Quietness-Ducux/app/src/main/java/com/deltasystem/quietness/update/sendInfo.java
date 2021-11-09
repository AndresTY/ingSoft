package com.deltasystem.quietness.update;
import android.os.StrictMode;

import com.deltasystem.quietness.Encrypt.HashPasswd;
import com.deltasystem.quietness.sql.SQLRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class sendInfo {
	//URGENTE: generalize connection SQL
	private SQLRequest SqlR= new SQLRequest();
	String jdbcUrl = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5446905"; //DataBase DIR
	
	private int getI_id_user(String a) { //get user id
		String sql = String.format("SELECT row FROM users WHERE email==\"%s\" OR name==\"%s\"",a,a);
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql5446905","widbzrH47x"); //Connect to DB
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int user = result.getInt("row");
			return user;
			
		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
			return 0;
		}finally {
			  if (connection != null) {
				    try {
				      connection.close(); 
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				  }
			}
	}
	

	public void add_quiz(String email, String passwd, String quiz){ //add the quiz to the user
		HashPasswd hp = new HashPasswd();
		String sql = String.format("UPDATE clients SET quiz=\"%s\" WHERE (email=\"%s\" AND passwd=\"%s\");",quiz,email,hp.hashed(passwd,"SHA-256"));
		int result;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,"sql5446905","widbzrH47x"); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void add_sueno(String email, String passwd, String sleep){ //add the quiz to the user
		HashPasswd hp = new HashPasswd();
		String sql = String.format("UPDATE clients SET sleep_register =\"%s\" WHERE (email = \"%s\" AND passwd = \"%s\");",sleep,email,hp.hashed(passwd,"SHA-256"));
		int result;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,"sql5446905","widbzrH47x"); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(sql);
		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//traer sueño y ya XD
	public String get_sleep_register(String a) { //get user id
		String sql = String.format("SELECT sleep_register FROM clients WHERE email = \"%s\"",a);
		Connection connection = null;
		ResultSet result = null;
		String aux = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql5446905","widbzrH47x"); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			while (result.next()) { //extracts the information from the database
				aux = result.getString("sleep_register");
			}

			return aux;

		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
			return "";
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
