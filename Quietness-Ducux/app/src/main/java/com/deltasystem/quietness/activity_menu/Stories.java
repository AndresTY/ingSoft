package com.deltasystem.quietness.activity_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.sql.SQLRequest;

import java.util.HashMap;
import java.util.Locale;

public class Stories extends AppCompatActivity {

    private Button btn_back,btn_play,btn_stop, btn_refresh;
    private TextView txt;
    private Bundle ble=null;
    private TextToSpeech tts;
    private SeekBar SBPitch,SBSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);
        initialization_btn();


        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_txt();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop_speak();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_menu();
            }
        });



    }

    private void get_txt(){
        ble = this.getIntent().getExtras();
        int aux=0;
        HashMap<String, Integer> point=new HashMap<String, Integer>() {{
            put("Playa", 1);
            put("Bosque", 2);
            put("Montana", 3);
            put("Ciudad", 4);
            put("Familia", 1);
            put("Amigos", 2);
            put("Mascota", 3);
            put("Pareja", 4);
            put("Leon", 1);
            put("Aguila", 2);
            put("Lobo", 3);
            put("Tiburon", 4);
            put("Mono", 5);
            put("Leer", 1);
            put("Audio-visuales", 2);
            put("Deporte", 3);
            put("Fiesta", 4);
        }};;
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        SQLRequest sql = new SQLRequest();
        String inf[] = sql.get_info("clients",user,passwd);

        String quiz[] = inf[4].split(";");

        for(String a: quiz){
            aux+= point.get(a);
        }

        String hist = sql.get_hist(aux);
        txt.setText(hist);
    }


    private void stop_speak() {
        if(tts.isSpeaking()){
            tts.stop();
        }
    }

    private void speak() {
        String text = txt.getText().toString();
        float pitch = (float) SBPitch.getProgress()/50;
        if(pitch<0.1) pitch = 0.1f;
        float speed = (float) SBSpeed.getProgress()/50;
        if(speed<0.1) speed = 0.1f;

        tts.setPitch(pitch);
        tts.setSpeechRate(speed);
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();

    }

    private void initialization_btn(){
        btn_back = (Button) findViewById(R.id.btnBack);
        btn_play = (Button) findViewById(R.id.Play_story);
        btn_stop = (Button) findViewById(R.id.stop_story);
        btn_refresh = findViewById(R.id.refresh_hist);
        txt = findViewById(R.id.ReadTxt);
        SBPitch = findViewById(R.id.pitch);
        SBSpeed = findViewById(R.id.spped);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i==TextToSpeech.SUCCESS){
                    Locale es = new Locale("spa","COL");
                    int result = tts.setLanguage(es);

                    if(result == TextToSpeech.LANG_MISSING_DATA || result== TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported" );
                    }else{
                        btn_play.setEnabled(true);
                    }
                }else{
                    Log.e("TTS", "Initialization failed" );
                }
            }
        });

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