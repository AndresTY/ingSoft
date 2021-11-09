package com.deltasystem.quietness.activity_menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.drawer.IDrawer;
import com.deltasystem.quietness.sueno.Main_Activity_Sueno;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.toolbar_items.settings;

import java.util.Calendar;

public class CalendarView extends AppCompatActivity implements IDrawer {

    private EditText title,location,desc;
    private Button saveEvent,back, btn_profile;
    private Bundle ble = null;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        initialization_all();
        ble = this.getIntent().getExtras();
        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar_action();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_profile();
            }
        });

    }

    private void open_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(CalendarView.this, Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private void calendar_action(){
        //verifies that all boxes are filled out
        if(!title.getText().toString().isEmpty()
                && !location.getText().toString().isEmpty()
                && !desc.getText().toString().isEmpty()
                /*&& !startDate.getText().toString().isEmpty()
                && !endDate.getText().toString().isEmpty()*/){

            //calls the calendar function
            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
            intent.putExtra(CalendarContract.Events.DESCRIPTION,desc.getText().toString());
            intent.putExtra(Intent.EXTRA_EMAIL,ble.getString("user").toString());
            startActivity(intent);

        }else{
            Toast.makeText(CalendarView.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }
    }

    //initialization of all wigdets
    private void initialization_all(){
        title = findViewById(R.id.TitleTxt);
        location = findViewById(R.id.LocationTxt);
        desc = findViewById(R.id.descriptionTxt);
        saveEvent = findViewById(R.id.save_Btn);
        back = findViewById(R.id.bck);
        btn_profile = findViewById(R.id.btn_Profile);
        drawerLayout = findViewById(R.id.drawer_layout);
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
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}