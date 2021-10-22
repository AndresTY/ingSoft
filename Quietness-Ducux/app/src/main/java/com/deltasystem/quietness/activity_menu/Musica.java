package com.deltasystem.quietness.activity_menu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.deltasystem.quietness.R;

public class Musica extends AppCompatActivity {

    private Button _bt1;
    private Button _bt3;
    private Button _bt4;
    private Button _bt5;
    private Button _bt6;
    private Button _bt7;
    private Button _reset;
    private MediaPlayer mp;
    private Bundle ble =null;
    private TextView song_txt;

    MediaPlayer vectmp [] = new MediaPlayer[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musica);

        initialize();
        load_songs();

        _bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(0,"Cascada");
            }
        });


        _bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(1,"Vacio");
            }
        });

        _bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(2,"Lluvia");
            }
        });

        _bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(3,"Olas");
            }
        });

        _bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(4, "viento");
            }
        });

        _bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Play(5,"Ballena");
            }
        });


        _reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }

    private void initialize(){

        _bt1 = (Button) findViewById(R.id.song1);
        _bt3 = (Button) findViewById(R.id.song3);
        _bt4 = (Button) findViewById(R.id.song4);
        _bt5 = (Button) findViewById(R.id.song5);
        _bt6 = (Button) findViewById(R.id.song6);
        _bt7 = (Button) findViewById(R.id.song7);
        _reset = (Button) findViewById(R.id.reset);
        song_txt = findViewById(R.id.SongPlaying);

    }

    private void load_songs(){

        vectmp[0] = MediaPlayer.create(this, R.raw.c_song);
        vectmp[1] = MediaPlayer.create(this, R.raw.v_song);
        vectmp[2] = MediaPlayer.create(this, R.raw.ll_song);
        vectmp[3] = MediaPlayer.create(this, R.raw.o_song);
        vectmp[4] = MediaPlayer.create(this, R.raw.vi_song);
        vectmp[5] = MediaPlayer.create(this, R.raw.b_song);

    }


    private void set_song_txt(String name, boolean is_playing){
        //Drawable song_drawable = AppCompatResources.getDrawable(this, R.drawable.song_txt);
        //Drawable wrappedDrawable = DrawableCompat.wrap(song_drawable);
        GradientDrawable gd = new GradientDrawable();

        if(!is_playing) {
            //DrawableCompat.setTint(wrappedDrawable, Color.GREEN);
            song_txt.setText(name);
            gd.setColor(Color.WHITE);
            gd.setStroke(8,Color.GREEN);
            gd.setCornerRadius(100);
            song_txt.setBackground(gd);
        }else{
            //DrawableCompat.setTint(wrappedDrawable, Color.RED);
            gd.setColor(Color.WHITE);
            gd.setStroke(8,Color.RED);
            gd.setCornerRadius(100);
            song_txt.setBackground(gd);
            song_txt.setText("NONE");
        }
    }

    private void Play(int i,String song){

        if(vectmp[i].isPlaying()){

            set_song_txt(song,true);
            vectmp[i].pause();
            reset();
            Toast.makeText(this,"Pausa",Toast.LENGTH_SHORT).show();

        }else{

            set_song_txt(song,false);
            reset();
            vectmp[i].start();
            vectmp[i].setLooping(true);
            Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();

        }

    }

    private void reset(){
        for(MediaPlayer a:vectmp){
            if(a.isPlaying())
                a.stop();
            a.release();
        }
        load_songs();
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