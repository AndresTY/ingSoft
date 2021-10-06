package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Stories extends AppCompatActivity {

    private Button btn_back, l1,l2,l3,l4;
    private Bundle ble=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        initializationBtn();
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://arxiv.org/pdf/2108.07387.pdf"));
                startActivity(bintent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ergoplatform.org/docs/whitepaper.pdf"));
                startActivity(bintent);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.reddit.com/r/programming/comments/byuoel/linus_torvalds_thinks_java_is_a_horrible_language/"));
                startActivity(bintent);
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/questions/961942/what-is-the-worst-programming-language-you-ever-worked-with"));
                startActivity(bintent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });
    }


    private void initializationBtn(){
        btn_back = (Button) findViewById(R.id.btnBack);
        l1 = findViewById(R.id.BL1);
        l2 = findViewById(R.id.BL2);
        l3 = findViewById(R.id.BL3);
        l4 = findViewById(R.id.BL4);

    }

    private void open_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Stories.this, Menu.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
}