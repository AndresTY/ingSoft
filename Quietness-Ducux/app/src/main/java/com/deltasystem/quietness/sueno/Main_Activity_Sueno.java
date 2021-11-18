package com.deltasystem.quietness.sueno;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.deltasystem.quietness.toolbar_items.BugReport;
import com.deltasystem.quietness.toolbar_items.Logout;
import com.deltasystem.quietness.toolbar_items.Profile;
import com.deltasystem.quietness.toolbar_items.settings;

public class Main_Activity_Sueno extends AppCompatActivity implements IDrawer {

    DrawerLayout drawerLayout;

    private Button btn_profile, back, load_h;
    private ImageButton btn_grafico;
    private TextView iniTime;
    private TextView finTime;
    private TextView notificationsTime;
    private ImageView number1;
    private ImageView number2;
    private ImageView number3;
    private ImageView number4;
    private ImageView number5;
    private TextView num_semana;
    private TextView rang_semana;
    private TextView prom_semana;

    private registro_hora hora;
    //private registro_alarma hora_alarma;
    private EstadisticaSemanal estad;
    private SharedPreferences settings;
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
        iniTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempo_inicial();
            }
        });
        finTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempo_final();
            }
        });
        notificationsTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_alarma();
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

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal(1);
            }
        });
        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal(2);
            }
        });
        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal(3);
            }
        });
        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal(4);
            }
        });
        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadistica_semanal(5);
            }
        });

        load_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargar_hora();
            }
        });

    }

    private void initialization_all() {
        back = findViewById(R.id.volverMenu);
        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String hour, minute;
        hour = settings.getString("hour","");
        minute = settings.getString("minute","");
        notificationsTime = (TextView) findViewById(R.id.alarm_time);
        if(hour.length() > 0)
        {
            notificationsTime.setText(hour + ":" + minute);
        }

        String hourIn, minuteIn;

        hourIn = settings.getString("hour","");
        minuteIn = settings.getString("minute","");


        iniTime = (TextView) findViewById(R.id.Inicialtime);
        finTime = (TextView) findViewById(R.id.Finaltime);

        if(hourIn.length() > 0)
        {
            iniTime.setText(hourIn + ":" + minuteIn);
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("Ini_hour", hourIn);
            edit.putString("Ini_minute", minuteIn);
            edit.commit();
        }
        number1 = findViewById(R.id.number_1_sueno);
        number2 = findViewById(R.id.number_2_sueno);
        number3 = findViewById(R.id.number_3_sueno);
        number4 = findViewById(R.id.number_4_sueno);
        number5 = findViewById(R.id.number_5_sueno);

        num_semana = (TextView)findViewById(R.id.semana_sueno_num);
        rang_semana = (TextView)findViewById(R.id.semana_rango_sueno);
        prom_semana = (TextView)findViewById(R.id.prom_time);

        load_h = findViewById(R.id.load_hora);
        btn_profile = findViewById(R.id.btn_Profile);
        btn_grafico = findViewById(R.id.btn_grafica);
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

    @Override
    public void ClickError(View view) {
        redireccionar(this, BugReport.class);
    }
    public void ClickLogout(View view){
        redireccionar(this, Logout.class);
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

    public void tiempo_inicial(){
        hora=hora.getRegistro(this,settings,ble);
        hora.fecha_inicial(iniTime);
    }

    public void tiempo_final(){
        hora=hora.getRegistro(this,settings,ble);
        hora.fecha_final(finTime);

    }

    public void set_alarma(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, registro_alarma.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    public void estadistica_semanal(int i){
        estad=estad.getRegistro(this);
        estad.iniciar_promedios();
        estad.iniciar_rangos();
        estad.imprimir(i,num_semana,rang_semana,prom_semana);
    }

    public void cargar_hora(){
        hora=hora.getRegistro(this,settings,ble);
        hora.aceptar(iniTime,finTime);
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