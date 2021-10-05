package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deltasystem.quietness.login.Validador;

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
                    openMenu();
                } else {
                    onLoginFailed();
                }
            }
        });
    }

    private void openMenu(){
        Intent intent = new Intent(Login.this,null);
        startActivity(intent);
    }

    public boolean login() {
        if (!validateLogin()) {
            return false;
        }else {
            onLoginSuccess();
            _signupButtonLogin.setEnabled(false);
            return true;
        }


        //String email = _emailLoginText.getText().toString();
        //String password = _passwordLoginText.getText().toString();

    }
    public void onLoginSuccess() {
        Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_LONG).show();
        _signupButtonLogin.setEnabled(true);
    }
    public void onLoginFailed() {
        Toast.makeText(this, "Falló proceso de inicio de sesión", Toast.LENGTH_LONG).show();
        _signupButtonLogin.setEnabled(true);
    }
    public boolean validateLogin() {
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