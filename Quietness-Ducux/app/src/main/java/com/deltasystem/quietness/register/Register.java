package com.deltasystem.quietness.register;

import com.deltasystem.quietness.Encrypt.HashPasswd;
import com.deltasystem.quietness.apis.*;
import com.deltasystem.quietness.sql.*;

public class Register {
	private SQLRequest SqlR = new SQLRequest();
	private ManualRegisterAPI registerApi;
	private HashPasswd hp = new HashPasswd();

	public void register_user_manual( String name, String username, String email, String passwd, char gender,String quiz, String sleep) { //Register user
		String password = hp.hashed(passwd,"SHA-256").toString(); //the encryption function is called with the SHA-256 algorithm.
		registerApi = new ManualRegisterAPI( name, username, email, password, gender,quiz,sleep);
		SqlR.insert_user(String.format("%s", registerApi.register())); //insert user

	}

}
