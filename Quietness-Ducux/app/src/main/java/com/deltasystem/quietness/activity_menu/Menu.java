package com.deltasystem.quietness.activity_menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.deltasystem.quietness.drawer.Drawer;
import com.deltasystem.quietness.drawer.IDrawer;
import com.deltasystem.quietness.sueno.Main_Activity_Sueno;
import com.deltasystem.quietness.toolbar_items.AboutUs;
import com.deltasystem.quietness.toolbar_items.Logout;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.toolbar_items.settings;

public class Menu extends AppCompatActivity implements IDrawer {

    DrawerLayout drawerLayout;

    private Button btn_profile;
    private ImageButton btnMusic,btnSleep,btnCalendar,btnStory,btn_tips;
    private Bundle ble=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);

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
        btnSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Sleep();
            }
        });
        btn_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_Tips();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_profile();
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
            logout();
        } else if (id == R.id.Profile){
            //Toast.makeText(this,"Profile",Toast.LENGTH_SHORT).show();
            open_profile();
        }
        return true;
    }

    public void ClickMenu(View view){
        // Abrir drawer
        openDrawer(drawerLayout);
    }

    public void openDrawer(DrawerLayout drawerLayout) {
        //Abrir Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Cerrar drawer
        closeDrawer(drawerLayout);
    }

    public void closeDrawer(DrawerLayout drawerLayout) {
        //Cerrar Drawer Layout
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //Si se encuentra abierto se cierra
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        redireccionar(this, Menu.class);
    }

    public void ClickMusica(View view){
        redireccionar(this, Musica.class);
    }

    public void ClickCalendario(View view) {
        redireccionar(this, CalendarView.class);
    }

    public void ClickTips(View view){
        redireccionar(this, Tips.class);
    }

    public void ClickSettings(View view) {
        redireccionar(this, settings.class);
    }

    public void ClickSueno(View view){
        redireccionar(this, Main_Activity_Sueno.class);
    }

    public void redireccionar(Activity activity, Class aClass) {
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(activity, aClass);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
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

    private void logout (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Logout.class);
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

    private void open_Sleep(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Main_Activity_Sueno.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void open_Tips(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Menu.this, Tips.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void toolbar_config(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initialize_btn(){
        btnMusic = (ImageButton) findViewById(R.id.BtnMusic);
        btnCalendar = (ImageButton) findViewById(R.id.BtnCalendar);
        btnSleep = (ImageButton) findViewById(R.id.BtnSleep);
        btnStory = (ImageButton) findViewById(R.id.BtnHistoria);
        btn_tips = (ImageButton) findViewById(R.id.Btn_tips);
        btn_profile = findViewById(R.id.btn_Profile);
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}