package com.deltasystem.quietness.sueno;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.deltasystem.quietness.EstadisticaDiaria.GraficoHora;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.CalendarView;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.activity_menu.Musica;
import com.deltasystem.quietness.activity_menu.Tips;
import com.deltasystem.quietness.alarma.registro_alarma;
import com.deltasystem.quietness.drawer.Drawer;
import com.deltasystem.quietness.drawer.IDrawer;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.toolbar_items.settings;

public class Main_Activity_Sueno extends AppCompatActivity implements IDrawer {

    DrawerLayout drawerLayout;

    private Button btn_profile, back;
    private ImageButton btn_est_semanal, btn_registro_hora, btn_registro_alar, btn_grafico;
    private Bundle ble = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sueno);
        initialization_all();
        ble = this.getIntent().getExtras();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver_menu();
            }
        });
        btn_est_semanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal();
            }
        });
        btn_registro_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro_hora();
            }
        });
        btn_registro_alar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro_alarma();
            }
        });
        btn_grafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grafico_hora();
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
        back = findViewById(R.id.volverMenu);
        btn_est_semanal = findViewById(R.id.btn_estadistica_mensual);
        btn_registro_hora = findViewById(R.id.btn_registro_manual);
        btn_registro_alar = findViewById(R.id.btn_registro_alarma);
        btn_grafico = findViewById(R.id.btn_grafica);
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

    @Override
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

    public void estadistica_semanal(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, EstadisticaSemanal.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void registro_hora(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, registro_hora.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void registro_alarma(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, registro_alarma.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void volver_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void grafico_hora(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, GraficoHora.class);
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