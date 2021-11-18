package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.login.*;
import com.deltasystem.quietness.register.Register;
import com.deltasystem.quietness.update.sendInfo;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Login extends AppCompatActivity {
    EditText _emailLoginText = null;
    EditText _passwordLoginText = null;
    Button _signupButtonLogin, _signUp_btn;
    Button _signupButtonGoogle = null;
    private SharedPreferences settings;
    private GoogleSignInClient mGoogleSignInClient;
    private boolean isdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        _emailLoginText = findViewById(R.id.correo_login);
        _passwordLoginText = findViewById(R.id.password_login);
        _signupButtonLogin = findViewById(R.id.button_login);
        _signUp_btn = findViewById(R.id.singup_btn_go);
        _signupButtonGoogle = findViewById(R.id.button_login_google);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        _signupButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

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

        _signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_signUp();
            }
        });

    }

    private void go_signUp() {
        Intent intent = new Intent(Login.this, SignUp.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void open_menu(View v) {
        if (isdata) {
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("Txt", "New");
            edit.putBoolean("Login", true);
            edit.commit();
        } else {
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("Txt", "Now");
            edit.putBoolean("Login", true);
            edit.commit();
        }
        String email = _emailLoginText.getText().toString();
        String password = _passwordLoginText.getText().toString();
        Intent intent = new Intent(Login.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        SharedPreferences.Editor edit = settings.edit();
        edit.putString("user", email);
        edit.putString("passwd", password);
        edit.commit();
        intent.putExtra("user", email);
        intent.putExtra("passwd", password);
        startActivity(intent);
    }


    public boolean login() {

        if (!validate_login()) {
            return false;
        } else {
            onLoginSuccess();
            _signupButtonLogin.setEnabled(false);
            return true;
        }

    }

    public void onLoginSuccess() {
        sleep_registrer(_emailLoginText.getText().toString());
        Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
        _signupButtonLogin.setEnabled(true);
    }

    private void sleep_registrer(String email) {
        sendInfo si = new sendInfo();
        String data = si.get_sleep_register(email);
        if (!data.equals(" ")) {
            isdata = true;
            escribir_contenido(data);
        } else {
            isdata = false;
        }
    }

    public void escribir_contenido(String data) {
        String archivos[] = fileList();
        try {

            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
            archivo.write(data);
            archivo.flush();
            archivo.close();

        } catch (IOException e) {

        }

    }

    private boolean archivoExiste(String archivos[], String nombre_archivo) {
        for (int i = 0; i < archivos.length; i++)
            if (archivos[i].equalsIgnoreCase(nombre_archivo))
                return true;
        return false;
    }

    public void onLoginFailed() {
        Toast.makeText(this, "Falló proceso de inicio de sesión", Toast.LENGTH_SHORT).show();
        _signupButtonLogin.setEnabled(true);
    }

    public boolean validate_login() {
        boolean valid = true;
        boolean entry = false;
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

        if (valid) {
            entry = validarUsuario(email, password);
        }

        return entry;
    }

    public boolean validarUsuario(String email, String password) {
        Validador v = new Validador();

        return v.validar(email, password);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Intent intent;

                SharedPreferences.Editor edit = settings.edit();
                edit.putString("user", account.getEmail());
                edit.putString("passwd", account.getId());
                if (!validarUsuario(account.getEmail(), account.getId())) {
                    intent = new Intent(Login.this, TermOfService.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    String username = account.getEmail().substring(0, account.getEmail().indexOf("@"));
                    Register reg = new Register();
                    edit.putBoolean("Login",true);
                    reg.register_user_manual(account.getDisplayName(), username, account.getEmail(), account.getId(), 'N', " ", " ");
                } else {

                    intent = new Intent(Login.this, Menu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);


                    sleep_registrer(account.getEmail());
                    if(isdata){

                        edit.putString("Txt", "New");


                    }else{
                        edit.putString("Txt", "Now");
                    }
                    edit.putBoolean("Login",true);
                }
                edit.commit();
                intent.putExtra("user", account.getEmail());
                intent.putExtra("passwd", account.getId());
                Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "Fallo de proceso de inicio de sesion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }

}