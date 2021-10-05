package com.deltasystem.quietness.register;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;

import com.deltasystem.quietness.MainActivity;
import com.deltasystem.quietness.apis.IRegister;
import com.deltasystem.quietness.apis.ManualRegisterAPI;
import com.deltasystem.quietness.sql.SQLRequest;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Register {
	private SQLRequest SqlR = new SQLRequest();
	private IRegister registerApi;


	public void registerUserManual( String name, String username, String email, String passwd, int phone) { //Register user
		registerApi = new ManualRegisterAPI( name, username, email, passwd, phone);

		SqlR.insertUser(String.format("%s", registerApi.register())); //insert user
	}

	public void registroManual(String name, String email, String passwd, char sex) throws IOException{

		String usuario =String.format("%s,%s,%s,%c",name,email,passwd,sex);
		String ruta = "archivo.txt";
		File archivo = new File(ruta);
		System.out.println(archivo.exists());
		BufferedWriter bw=null;
		if(archivo.exists()) {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write("El fichero de texto ya estaba creado.");

		} else {
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write("Acabo de crear el fichero de texto.");
		}
		bw.close();
	}



}
