package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.activity_menu.Musica;
import com.deltasystem.quietness.activity_menu.Stories;
import com.deltasystem.quietness.sing_in_up.Login;
import com.deltasystem.quietness.sing_in_up.SignUp;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                //Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}