package com.deltasystem.quietness.update;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deltasystem.quietness.Encrypt.HashPasswd;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.sing_in_up.Encuesta;
import com.deltasystem.quietness.sing_in_up.TermOfService;
import com.deltasystem.quietness.sql.SQLRequest;
import com.deltasystem.quietness.toolbar_items.Profile;

public class update_view extends AppCompatActivity {

    private EditText name_edit, user_edit, emai_edit, gender_edit;
    private Button back_btn, update_btn;
    private SQLRequest sql=new SQLRequest();
    private Bundle ble;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_view);

        ble = this.getIntent().getExtras();
        initialization();
        showInfo();

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdate();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBack();
            }
        });

    }

    private void showInfo() {
        HashPasswd hp = new HashPasswd();
        String[] str = new String[5];
        ble=this.getIntent().getExtras();
        String password = hp.hashed(ble.getString("passwd"),"SHA-256").toString();

        str = sql.get_info("clients",ble.getString("user"), password);
        if(str!=null) {
            for (String a:str) {
                Log.e("STR",a);
            }

            name_edit.setText(str[1]);
            user_edit.setText(str[0]);
            emai_edit.setText(str[2]);
            gender_edit.setText(str[3]);
        }
    }

    public void initialization(){
        name_edit= (EditText) findViewById(R.id.editName);
        user_edit= (EditText) findViewById(R.id.editUsername);
        emai_edit= (EditText) findViewById(R.id.editEmail);
        gender_edit= (EditText) findViewById(R.id.editGender);

        back_btn = findViewById(R.id.buttonBack);
        update_btn = findViewById(R.id.buttonUpdate);
    }

    private void update(){
        HashPasswd hp = new HashPasswd();
        String password = hp.hashed(ble.getString("passwd"),"SHA-256").toString();
        sql.update_user(name_edit.getText().toString(),user_edit.getText().toString(),emai_edit.getText().toString(),gender_edit.getText().toString(),ble.getString("user"),password);
    }

    public void onClickUpdate(){
        update();
        ble = this.getIntent().getExtras();
        Intent intent = new Intent(update_view.this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",emai_edit.getText().toString());
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
    public void onClickBack(){
        ble = this.getIntent().getExtras();
        Intent intent = new Intent(update_view.this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
}