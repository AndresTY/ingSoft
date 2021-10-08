package com.deltasystem.quietness.activity_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.deltasystem.quietness.toolbar_items.AboutUs;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.toolbar_items.settings;

public class Menu extends AppCompatActivity {

    private Button btnMusic,btnSleep,btnCalendar,btnStory;
    private Bundle ble=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initialize_btn();
        toolbar_config();

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Music();
            }
        });
        btnStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Stories();
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Calendar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.Settings){
            //Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
            open_settings();
        } else if (id == R.id.About) {
            //Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            open_about();
        } else if (id == R.id.Logout){
            Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Profile){
            //Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
            open_profile();
        }
        return true;
    }

    private void open_profile (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Profile.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_about (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, AboutUs.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_settings (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, settings.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_Music(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Musica.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_Stories(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Stories.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_Calendar(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, CalendarView.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void toolbar_config(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initialize_btn(){
        btnMusic = (Button) findViewById(R.id.BtnMusic);
        btnCalendar = (Button) findViewById(R.id.BtnCalendar);
        btnSleep = (Button) findViewById(R.id.BtnSleep);
        btnStory = (Button) findViewById(R.id.BtnHistoria);
    }

}