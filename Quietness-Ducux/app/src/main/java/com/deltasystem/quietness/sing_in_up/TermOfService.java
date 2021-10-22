package com.deltasystem.quietness.sing_in_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.R;

public class TermOfService extends AppCompatActivity {

    Button Abtn = null;
    Bundle ble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_service);

        config_display();

        Abtn = findViewById(R.id.acceptBtn);
        Abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu(view);
            }
        });
    }

    private void config_display(){
        setContentView(R.layout.activity_term_of_service);
        DisplayMetrics medidas = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidas);
        getWindow().setLayout((int)(0.85*medidas.widthPixels),(int)(0.65*medidas.heightPixels));
    }

    private void open_menu(View v){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(TermOfService.this, Encuesta.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        intent.putExtra("view",0);
        startActivity(intent);
    }
}