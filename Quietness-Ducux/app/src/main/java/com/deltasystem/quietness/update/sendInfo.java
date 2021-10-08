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
	String jdbcUrl = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3442286"; //DataBase DIR
	
	private int getI_id_user(String a) { //get user id
		String sql = String.format("SELECT row FROM users WHERE email==\"%s\" OR name==\"%s\"",a,a);
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
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
	
	public void updateInfo(String name, String passwd, String Change) {  //update the user
		UpdateUser u = new UpdateUser(); 
		SqlR.update_user(String.format("%s",u.update(name,passwd)),getI_id_user(Change));
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
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
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
}
