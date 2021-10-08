package com.deltasystem.quietness.toolbar_items;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deltasystem.quietness.Encrypt.HashPasswd;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.activity_menu.Menu;
import com.deltasystem.quietness.sql.SQLRequest;

public class Profile extends AppCompatActivity {

    private TextView userTxt,nameTxt,emailTxt;
    private Button btn_back;
    private Bundle bdle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialization_txt();
        initialization_btn();
        info();


    }

    private void initialization_txt(){
        userTxt = findViewById(R.id.username_text);
        nameTxt = findViewById(R.id.name_text);
        emailTxt = findViewById(R.id.email_Text);
    }

    private void initialization_btn(){
       // btn_back = (Button) findViewById(R.id.button2);
    }

    private void open_menu(View v){
        bdle = this.getIntent().getExtras();
        String user = bdle.getString("user");
        String passwd = bdle.getString("passwd");
        Intent intent = new Intent(Profile.this, Menu.class);
        intent.putExtra("user",bdle.getString("user"));
        intent.putExtra("passwd",bdle.getString("passwd"));
        startActivity(intent);
    }

    private void info(){
        HashPasswd hp = new HashPasswd();
        String[] str = new String[5];
        bdle=this.getIntent().getExtras();
        String password = hp.hashed(bdle.getString("passwd"),"SHA-256").toString();
        SQLRequest sql = new SQLRequest();
        str = sql.get_info("clients",bdle.getString("user"),password);

        if(str!=null) {
            userTxt.setText(str[0]);
            nameTxt.setText(str[1]);
            emailTxt.setText(str[2]);
        }

    }

}