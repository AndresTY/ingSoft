package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.deltasystem.quietness.register.Register;

import java.io.IOException;

public class SignUp extends AppCompatActivity {
    Spinner opciones;
    EditText _nameText = null;
    EditText _lastnameText = null;
    EditText _userText = null;
    EditText _emailText = null;
    EditText _passwordText = null;
    EditText _reEnterPasswordText = null;
    Button _signupButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
                    signup(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void get_info_boxes(){
        _nameText = findViewById(R.id.nombre);
        _lastnameText = findViewById(R.id.apellido);
        _userText = findViewById(R.id.usuario);
        _emailText = findViewById(R.id.correo);
        _passwordText = findViewById(R.id.password_id);
        _reEnterPasswordText = findViewById(R.id.checkpassw);
        _signupButton = findViewById(R.id.button);
    }

    public void signup(View v) throws IOException {

        String name = _nameText.getText().toString();
        String lastName = _lastnameText.getText().toString();
        String user = _userText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String re_password = _reEnterPasswordText.getText().toString();

        if (!validate(name,lastName,user,email,password,re_password)) {
            onSignupFailed();
            return;
        }else {

            Register register = new Register();
            char genre = opciones.getSelectedItem().toString().charAt(0);
            String nombreCompleto = name + " " + lastName;
            register.register_user_manual(nombreCompleto, user, email, password, genre, " ");
            onSignupSuccess();
            onClickRegister(v);

        }

    }

    public void onSignupSuccess() {
        Toast.makeText(this, "Proceso de registro exitoso", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public void onSignupFailed() {
        Toast.makeText(this, "Falló proceso de registro", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }

    public boolean validate(String name,String lastname,String user,String email,String password,String re_password) {
        boolean valid = true;

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Introduce al menos tres caracteres");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (lastname.isEmpty()) {
            _lastnameText.setError("Introduce un apellido");
            valid = false;
        } else {
            _lastnameText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Introduce una dirección de email válida");
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
            _passwordText.setError("La contraseña debe ser entre 4 y 12 caracteres alfanumericos");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (re_password.isEmpty() || re_password.length() < 4 || re_password.length() > 12 || !(re_password.equals(password))) {
            _reEnterPasswordText.setError("Las contraseñas no coinciden");
            valid = false;
        } else {
            _reEnterPasswordText.setError(null);
        }

        return valid;
    }

    public void onClickLogin(View view){
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }
    public void onClickRegister(View view){
        Intent intent = new Intent(SignUp.this, TermOfService.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        intent.putExtra("user",email);
        intent.putExtra("passwd",password);
        startActivity(intent);
    }
}