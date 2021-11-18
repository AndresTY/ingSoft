package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.activity_menu.Musica;
import com.deltasystem.quietness.activity_menu.Stories;
import com.deltasystem.quietness.sing_in_up.Login;
import com.deltasystem.quietness.sing_in_up.SignUp;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
                boolean login = false;

                Intent intent;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    // Si hay conexión a Internet en este momento
                    if (!settings.contains("Login")){
                        login =false;
                    }else{
                        login = settings.getBoolean("Login", true);
                    }

                    if(login){
                        String email = settings.getString("user","");
                        String password = settings.getString("passwd","");
                        intent = new Intent(MainActivity.this, Menu.class);
                        intent.putExtra("user", email);
                        intent.putExtra("passwd", password);
                    }else{
                        intent = new Intent(MainActivity.this, Login.class);
                    }

                    startActivity(intent);
                    finish();
                } else {
                    // No hay conexión a Internet en este momento
                    Toast.makeText(getApplicationContext(),"No hay internet en este momento",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        },1000);
    }
}