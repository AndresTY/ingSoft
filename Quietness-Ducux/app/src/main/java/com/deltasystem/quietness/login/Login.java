package com.deltasystem.quietness.login;

public class Login {

	public void ingresar(String email, String passwd) {
		Validador validator = new Validador(); //validate the user
		if (validator.validar(email, passwd)){
			System.out.println("ingresas morsh");
		}else {
			System.out.println("no morsh asi no");
		}
	}
}
