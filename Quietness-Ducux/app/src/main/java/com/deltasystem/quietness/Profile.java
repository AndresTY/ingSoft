package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deltasystem.quietness.sql.SQLRequest;

public class Profile extends AppCompatActivity {

    private TextView userTxt,nameTxt,emailTxt;
    private Button btn_back;
    private Bundle bdle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializationTxt();
        initializationBtn();
        info();


    }

    private void initializationTxt(){
        userTxt = findViewById(R.id.username_text);
        nameTxt = findViewById(R.id.name_text);
        emailTxt = findViewById(R.id.email_Text);
    }

    private void initializationBtn(){
        btn_back = (Button) findViewById(R.id.button2);
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
        String[] str = new String[5];
        bdle=this.getIntent().getExtras();
        SQLRequest sql = new SQLRequest();
        str = sql.getInfo("clients",bdle.getString("user"),bdle.getString("passwd"));
        if(str!=null) {
            userTxt.setText(str[0]);
            nameTxt.setText(str[1]);
            emailTxt.setText(str[2]);
        }
    }

}