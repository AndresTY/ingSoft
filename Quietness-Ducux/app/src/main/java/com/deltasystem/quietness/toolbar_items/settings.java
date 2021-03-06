package com.deltasystem.quietness.toolbar_items;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.alarma.Utils;
import com.deltasystem.quietness.sing_in_up.Encuesta;
import com.deltasystem.quietness.sing_in_up.TermOfService;

public class settings extends AppCompatActivity {

    private Button back,quiz, politicas;
    private Switch notification;
    private SharedPreferences settings;
    private Bundle ble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        initialization_btn();

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_quiz();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });
        politicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_terms_of_service();
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_alarm();
            }
        });

    }

    private void open_quiz(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(settings.this, Encuesta.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        intent.putExtra("view",1);
        startActivity(intent);
    }
    private void open_terms_of_service(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(settings.this, Politicas.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(settings.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
    public void update_alarm(){
        if(notification.isChecked()){
            if(settings.contains("alarmID")){
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("sound", "yes");
                edit.commit();
                Utils.setAlarm(settings.getInt("alarmID", 0),settings.getString("sound",""), settings.getLong("alarmTime", 0), this);
            }

        }else{

            if(settings.contains("alarmID")){
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("sound", "no");
                edit.commit();
                Utils.setAlarm(settings.getInt("alarmID", 0),settings.getString("sound",""), settings.getLong("alarmTime", 0), this);
            }

        }
    }

    private void initialization_btn(){
        back = findViewById(R.id.back_ntb);
        quiz = findViewById(R.id.quiz_ntb);
        politicas = findViewById(R.id.term_ntb);
        notification = findViewById(R.id.notificationSet);
        notification.setChecked(true);
    }
}