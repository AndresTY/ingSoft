package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.login.*;
import com.deltasystem.quietness.update.sendInfo;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Login extends AppCompatActivity {
    EditText _emailLoginText = null;
    EditText _passwordLoginText = null;
    Button _signupButtonLogin = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _emailLoginText = findViewById(R.id.correo_login);
        _passwordLoginText = findViewById(R.id.password_login);
        _signupButtonLogin = findViewById(R.id.button_login);
        _signupButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login()) {
                    open_menu(v);
                } else {
                    onLoginFailed();
                }
            }
        });
    }


    private void open_menu(View v){
        String email = _emailLoginText.getText().toString();
        String password = _passwordLoginText.getText().toString();
        Intent intent = new Intent(Login.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",email);
        intent.putExtra("passwd",password);
        startActivity(intent);
    }


    public boolean login() {

        if (!validate_login()) {
            return false;
        }else {
            onLoginSuccess();
            _signupButtonLogin.setEnabled(false);
            return true;
        }

    }

    public void onLoginSuccess() {
        sleep_registrer();
        Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
        _signupButtonLogin.setEnabled(true);
    }

    private void sleep_registrer(){
        sendInfo si = new sendInfo();
        String data = si.get_sleep_register(_emailLoginText.getText().toString());
        escribir_contenido(data);
    }

    public void escribir_contenido(String data){
        String archivos [] = fileList();

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                archivo.write(data);
                archivo.flush();
                archivo.close();

            }catch (IOException e){

            }
        }
    }

    private boolean archivoExiste(String archivos [], String nombre_archivo){
        for(int i = 0; i < archivos.length; i++)
            if(archivos[i].equalsIgnoreCase(nombre_archivo))
                return true;
        return false;
    }

    public void onLoginFailed() {
        Toast.makeText(this, "Falló proceso de inicio de sesión", Toast.LENGTH_SHORT).show();
        _signupButtonLogin.setEnabled(true);
    }

    public boolean validate_login() {

        Validador v = new Validador();
        boolean valid=true;
        boolean entry=false;
        String email = _emailLoginText.getText().toString();
        String password = _passwordLoginText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailLoginText.setError("Introduce una dirección de email válida");
            valid = false;
        } else {
            _emailLoginText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
            _passwordLoginText.setError("Ingresa tu contraseña");
            valid = false;
        } else {
            _passwordLoginText.setError(null);
        }

        if(valid){
            entry = v.validar(email,password);
        }

        return entry;
    }

}