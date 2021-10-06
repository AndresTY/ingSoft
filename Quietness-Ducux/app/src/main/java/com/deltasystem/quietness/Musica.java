package com.deltasystem.quietness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Musica extends AppCompatActivity {

    private Button _bt1;
    private ImageButton _bt2;
    private Button _bt3;
    private Button _bt4;
    private Button _bt5;
    private Button _bt6;
    private Button _bt7;
    private Button _bt8;
    private Button _reset;
    private MediaPlayer mp;
    private Bundle ble =null;

    MediaPlayer vectmp [] = new MediaPlayer[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);
        inicialite();
        loadSongs();
        _bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(0);
            }
        });

        _bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(1);
            }
        });
        _bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(2);
            }
        });
        _bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(3);
            }
        });
        _bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(4);
            }
        });
        _bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(5);
            }
        });
        _bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(6);
            }
        });
        _bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(7);
            }
        });
        _reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });
    }

    private void inicialite(){
        _bt1 = (Button) findViewById(R.id.song1);
        _bt2 = findViewById(R.id.song2);
        _bt3 = (Button) findViewById(R.id.song3);
        _bt4 = (Button) findViewById(R.id.song4);
        _bt5 = (Button) findViewById(R.id.song5);
        _bt6 = (Button) findViewById(R.id.song6);
        _bt7 = (Button) findViewById(R.id.song7);
        _bt8 = (Button) findViewById(R.id.song8);
        _reset = (Button) findViewById(R.id.reset);
    }

    private void loadSongs(){
        vectmp[0] = MediaPlayer.create(this, R.raw.s1);
        vectmp[1] = MediaPlayer.create(this, R.raw.s2);
        vectmp[2] = MediaPlayer.create(this, R.raw.s3);
        vectmp[3] = MediaPlayer.create(this, R.raw.s4);
        vectmp[4] = MediaPlayer.create(this, R.raw.s5);
        vectmp[5] = MediaPlayer.create(this, R.raw.s6);
        vectmp[6] = MediaPlayer.create(this, R.raw.s7);
        vectmp[7] = MediaPlayer.create(this, R.raw.s8);
    }

    private void Play(int i){
        if(vectmp[i].isPlaying()){
            vectmp[i].pause();
            Toast.makeText(this,"Pausa",Toast.LENGTH_SHORT).show();
        }else{
            vectmp[i].start();
            Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
        }
    }

    private void reset(){
        for(MediaPlayer a:vectmp){
            if(a.isPlaying())
                a.stop();
        }
    }

    private void open_menu(){
        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(Musica.this, Menu.class);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }
}