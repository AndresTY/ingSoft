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
	private String jdbcUrl = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5446905";	//URGENTE: generalize connection SQL

	public boolean validar(String email, String passwd) {
		String password = hp.hashed(passwd,"SHA-256").toString(); //the encryption function is called with the SHA-256 algorithm.
		String sql = String.format("SELECT * FROM clients WHERE (email=\"%s\" AND passwd=\"%s\")",email,password); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			//configures the database
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,"sql5446905","widbzrH47x"); //Connect to DB
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
