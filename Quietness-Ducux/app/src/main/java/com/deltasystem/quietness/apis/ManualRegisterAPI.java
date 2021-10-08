package com.deltasystem.quietness.apis;

public class ManualRegisterAPI implements IRegister { //Manual register

	private String name;
	private String email;
	private String passwd; //has been hashed
	private char gender; // Male Female Not to say
	private String username;
	private String quiz;

		
	public ManualRegisterAPI(String name,String username, String email, String passwd,char gender,String quiz) { //register constructor
		this.name = name;
		this.username=username;
		this.email = email;
		this.passwd = passwd;
		this.gender=gender;
		this.quiz=quiz;
	}

	public String register() { //sets the data for the SQL statement
		return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\'%c\',\"%s\"", name,username,email,passwd,gender,quiz);
	}
}
