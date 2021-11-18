package com.deltasystem.quietness.login;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;

import com.deltasystem.quietness.Encrypt.HashPasswd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Validador {
	private HashPasswd hp = new HashPasswd();
	private String host = "sql10.freesqldatabase.com";
	private String name = "sql10449029";
	private String pass="biAiMlF4ue";


	private String jdbcUrl = "jdbc:mysql://"+host+":3306/"+name; //DataBase DIR	//URGENTE: generalize connection SQL

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
			connection = DriverManager.getConnection(jdbcUrl,name, pass); //Connect to DB
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

	public boolean existeUsuario(String email) {
		String sql = String.format("SELECT * FROM `clients` WHERE (email=\"%s\")", email); //SQL statement
		ResultSet result;
		Connection connection = null;
		try {
			//configures the database
			Class.forName("com.mysql.jdbc.Driver");
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			connection = DriverManager.getConnection(jdbcUrl,name, pass); //Connect to DB
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			if (result.next() == false) {
				return false;
			}
			return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
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
