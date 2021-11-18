package com.deltasystem.quietness.activity_menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.drawer.IDrawer;
import com.deltasystem.quietness.sueno.Main_Activity_Sueno;
import com.deltasystem.quietness.tips.MainExposicion;
import com.deltasystem.quietness.tips.MainParcial;
import com.deltasystem.quietness.tips.MainProyecto;
import com.deltasystem.quietness.toolbar_items.BugReport;
import com.deltasystem.quietness.toolbar_items.Logout;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.toolbar_items.settings;

public class Tips extends AppCompatActivity implements IDrawer {

    private Button back, btn_parcial, btn_exp, btn_pro;
    private Button btn_profile;
    private Bundle ble = null;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        initialization_all();
        ble = this.getIntent().getExtras();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver_menu();
            }
        });
        btn_parcial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente_par();
            }
        });
        btn_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente_exp();
            }
        });
        btn_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguiente_pro();
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_profile();
            }
        });
    }

    private void initialization_all() {
        back = findViewById(R.id.btn_menu);
        btn_parcial = findViewById(R.id.btn_parcial);
        btn_exp = findViewById(R.id.btn_expo);
        btn_pro = findViewById(R.id.btn_proy);
        btn_profile = findViewById(R.id.btn_Profile);
        drawerLayout = findViewById(R.id.drawer_layout);

    }

    //Metodo Anterior 2
    public void volver_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    //Metodo boton
    public void siguiente_par (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, MainParcial.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void siguiente_exp (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, MainExposicion.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void siguiente_pro (){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, MainProyecto.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
    public void ClickMenu(View view){
        // Abrir drawer
        openDrawer(drawerLayout);
    }
    public void ClickLogout(View view){
        redireccionar(this, Logout.class);
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

    @Override
    public void ClickError(View view) {
        redireccionar(this, BugReport.class);
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

