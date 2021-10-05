package com.deltasystem.quietness.apis;

public class ManualRegisterAPI implements IRegister { //Manual register

	private String name;
	private String email;
	private String passwd;
	private int phone;
	private String username; // M F PND

		
	public ManualRegisterAPI(String name,String username, String email, String passwd,int phone) { //Constructor

		this.name = name;
		this.username=username;
		this.email = email;
		this.passwd = passwd;
		this.phone=phone;
	}

	public String register() { //sets the data for the SQL statement
		return String.format("\"%s\",\"%s\",\"%s\",\"%s\",%d", name,username,email,passwd,phone);
	}
}
