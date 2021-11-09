package com.deltasystem.quietness.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.activity_menu.Musica;
import com.deltasystem.quietness.activity_menu.Tips;
import com.deltasystem.quietness.sueno.Main_Activity_Sueno;

public class Drawer extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private Bundle ble=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        //Asiganar variables
        drawerLayout  = findViewById(R.id.drawer_layout);
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

    public void ClickTips(View view){
        redireccionar(this, Tips.class);
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
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}