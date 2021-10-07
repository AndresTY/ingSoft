package com.deltasystem.quietness.login;
import android.os.StrictMode;

import com.deltasystem.quietness.Encrypt.HashPasswd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validador {
	private HashPasswd hp = new HashPasswd();
	private String jdbcUrl = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql3442286";	//URGENTE: generalize connection SQL

	public boolean validar(String email, String passwd) {
		String password = hp.hashed(passwd,"SHA-256").toString();
		String sql = String.format("SELECT * FROM `clients` WHERE (email=\"%s\" AND passwd=\"%s\")",email,password); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,"sql3442286","qbM8XpxegR"); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			if(result.next() == false) {
				return false;
			}
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
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
