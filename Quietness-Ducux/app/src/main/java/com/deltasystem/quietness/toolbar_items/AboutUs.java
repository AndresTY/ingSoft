package com.deltasystem.quietness.toolbar_items;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;

public class AboutUs extends AppCompatActivity {

    private Button btnBack = null;
    private Bundle ble=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initialization_btn();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });

    }

    private void open_menu(){
        ble = this.getIntent().getExtras();
        //pass the main user data
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(AboutUs.this, Menu.class);
        //sent to the next view
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    //initialization buttons
    private void initialization_btn(){
        btnBack = (Button) findViewById(R.id.back_btn);
    }
}