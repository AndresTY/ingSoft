package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.login.Validador;
import com.deltasystem.quietness.register.Register;
import com.deltasystem.quietness.update.sendInfo;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class SignUp extends AppCompatActivity {
    Spinner opciones;
    EditText _nameText = null;
    EditText _userText = null;
    EditText _emailText = null;
    EditText _passwordText = null;
    EditText _reEnterPasswordText = null;
    Button _signupButton = null;
    Button _back_login;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        opciones = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.generos, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        get_info_boxes();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        // Si hay conexi??n a Internet en este momento
                        signup(v);
                    } else {
                        // No hay conexi??n a Internet en este momento
                        Toast.makeText(getApplicationContext(),"No hay internet en este momento",Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        _back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin(view);
            }
        });

    }

    private void get_info_boxes(){
        _nameText = findViewById(R.id.nombre);
        _userText = findViewById(R.id.usuario);
        _emailText = findViewById(R.id.correo);
        _passwordText = findViewById(R.id.password_id);
        _reEnterPasswordText = findViewById(R.id.checkpassw);
        _signupButton = findViewById(R.id.button);
        _back_login = findViewById(R.id.back_login_btn);
    }

    public void signup(View v) throws IOException {

        String name = _nameText.getText().toString();
        String user = _userText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String re_password = _reEnterPasswordText.getText().toString();

        if (!validate(name,user,email,password,re_password)) {
            onSignupFailed();
            return;
        }else {

            Register register = new Register();
            char genre = opciones.getSelectedItem().toString().charAt(0);
            register.register_user_manual(name, user, email, password, genre, " ", " ");
            onSignupSuccess();
            onClickRegister(v);

        }

    }

    public void onSignupSuccess() {
        Toast.makeText(this, "Proceso de registro exitoso", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public void onSignupFailed() {
        Toast.makeText(this, "Fall?? proceso de registro", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate(String name,String user,String email,String password,String re_password) {
        boolean valid = true;
        boolean entry = false;
        Validador v = new Validador();
        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Introduce al menos tres caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Introduce una direcci??n de email v??lida");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (user.isEmpty()) {
            _userText.setError("Introduce un usuario");
            valid = false;
        } else {
            _userText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 12) {
            _passwordText.setError("La contrase??a debe ser entre 4 y 12 caracteres alfanumericos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (re_password.isEmpty() || re_password.length() < 4 || re_password.length() > 12 || !(re_password.equals(password))) {
            _reEnterPasswordText.setError("Las contrase??as no coinciden");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }
        if(valid){
            entry = !v.existeUsuario(email);
            if(entry){
                _emailText.setError("Esta direcci??n de email ya existe");
            }else{
                _emailText.setError(null);
            }
        }
        return valid;
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(SignUp.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void onClickRegister(View view){
        SharedPreferences.Editor edit = settings.edit();
        edit.putString("Txt", "Now");
        edit.putBoolean("Login",true);
        Intent intent = new Intent(SignUp.this, TermOfService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        edit.putString("user", email);
        edit.putString("passwd", password);
        edit.commit();
        intent.putExtra("user",email);
        intent.putExtra("passwd",password);
        startActivity(intent);
    }
}