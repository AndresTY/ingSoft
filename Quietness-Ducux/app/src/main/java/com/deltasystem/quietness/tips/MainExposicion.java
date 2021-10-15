package com.deltasystem.quietness.tips;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Tips;

public class MainExposicion extends AppCompatActivity {

    private Button back;
    private Bundle ble = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exposicion);
        initialization_all();
        ble = this.getIntent().getExtras();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver_menu();
            }
        });
    }

    private void initialization_all() {
        back = findViewById(R.id.button2);
    }

    //Metodo Anterior 2
    public void volver_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, Tips.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
}