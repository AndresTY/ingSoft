package com.deltasystem.quietness.activity_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.sueno.Main_Activity_Sueno;
import com.deltasystem.quietness.tips.MainExposicion;
import com.deltasystem.quietness.tips.MainParcial;
import com.deltasystem.quietness.tips.MainProyecto;

public class Tips extends AppCompatActivity {

    private Button back, btn_parcial, btn_exp, btn_pro;
    private Bundle ble = null;

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
    }

    private void initialization_all() {
        back = findViewById(R.id.btn_menu);
        btn_parcial = findViewById(R.id.boton1);
        btn_exp = findViewById(R.id.boton2);
        btn_pro = findViewById(R.id.boton3);
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

}

