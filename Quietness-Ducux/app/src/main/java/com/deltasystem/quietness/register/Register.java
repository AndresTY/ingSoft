package com.deltasystem.quietness.register;

import com.deltasystem.quietness.Encrypt.HashPasswd;
import com.deltasystem.quietness.apis.IRegister;
import com.deltasystem.quietness.apis.ManualRegisterAPI;
import com.deltasystem.quietness.sql.SQLRequest;

public class Register {
	private SQLRequest SqlR = new SQLRequest();
	private IRegister registerApi;
	private HashPasswd hp = new HashPasswd();

	public void register_user_manual( String name, String username, String email, String passwd, char gender,String quiz) { //Register user
		String password = hp.hashed(passwd,"SHA-256").toString(); //the encryption function is called with the SHA-256 algorithm.
		registerApi = new ManualRegisterAPI( name, username, email, password, gender,quiz);
		SqlR.insert_user(String.format("%s", registerApi.register())); //insert user

	}

}
