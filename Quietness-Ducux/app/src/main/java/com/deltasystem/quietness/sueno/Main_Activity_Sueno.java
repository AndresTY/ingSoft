package com.deltasystem.quietness.sueno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


import com.deltasystem.quietness.EstadisticaDiaria.GraficoHora;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.CalendarView;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.alarma.registro_alarma;

public class Main_Activity_Sueno extends AppCompatActivity {

    private Button back, btn_est_semanal, btn_registro_hora, btn_registro_alar, btn_grafico;
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
    }

    private void initialization_all() {
        back = findViewById(R.id.volverMenu);
        btn_est_semanal = findViewById(R.id.btn_estadistica_mensual);
        btn_registro_hora = findViewById(R.id.btn_registro_manual);
        btn_registro_alar = findViewById(R.id.btn_registro_alarma);
        btn_grafico = findViewById(R.id.btn_grafica);
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
}