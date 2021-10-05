package com.deltasystem.quietness.update;

public class UpdateUser {
	public String update(String name, String passwd) {
		return String.format("name=\"%s\",passwd=\"%s\"",name,passwd); //return SQL statement Update
	}
}
