package com.deltasystem.quietness.sueno;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.EstadisticaDiaria.HoraDiaria;
import com.deltasystem.quietness.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class registro_hora extends AppCompatActivity {
    private TextView iniTime;
    private TextView finTime;
    private SharedPreferences settings;
    private Calendar date;
    private int sleep_h = 0;
    private int sleep_m = 0;
    private Button btn_ini, btn_fin, btn_aceptar;
    private Bundle ble = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hora);
        initialize_btn();
        ble = this.getIntent().getExtras();

        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        String hourIn, minuteIn;

        hourIn = settings.getString("hour","");
        minuteIn = settings.getString("minute","");


        iniTime = (TextView) findViewById(R.id.Inicialtime);
        finTime = (TextView) findViewById(R.id.Finaltime);

        if(hourIn.length() > 0)
        {
            iniTime.setText(hourIn + ":" + minuteIn);
            SharedPreferences.Editor edit = settings.edit();
            edit.putString("Ini_hour", hourIn);
            edit.putString("Ini_minute", minuteIn);
            edit.commit();
        }

        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecha_inicial();
            }
        });
        btn_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fecha_final();
            }
        });
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aceptar();
            }
        });
    }

    private void initialize_btn(){
        btn_ini = (Button) findViewById(R.id.Inicial);
        btn_fin = (Button) findViewById(R.id.Final);
        btn_aceptar = (Button) findViewById(R.id.Btn_aceptar);
    }

    private void fecha_inicial(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePickerInicial;
        mTimePickerInicial = new TimePickerDialog(registro_hora.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String finalHour, finalMinute;

                finalHour = "" + selectedHour;
                finalMinute = "" + selectedMinute;
                if (selectedHour < 10) finalHour = "0" + selectedHour;
                if (selectedMinute < 10) finalMinute = "0" + selectedMinute;
                iniTime.setText(finalHour + ":" + finalMinute);

                Calendar today = Calendar.getInstance();

                today.set(Calendar.HOUR_OF_DAY, selectedHour);
                today.set(Calendar.MINUTE, selectedMinute);
                today.set(Calendar.SECOND, 0);

                SharedPreferences.Editor edit = settings.edit();
                edit.putString("Ini_hour", finalHour);
                edit.putString("Ini_minute", finalMinute);

                edit.commit();

                Toast.makeText(registro_hora.this, getString(R.string.changed_to, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePickerInicial.setTitle(getString(R.string.select_time));
        mTimePickerInicial.show();
    }

    private void fecha_final(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePickerFinal;
        mTimePickerFinal = new TimePickerDialog(registro_hora.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String finalHour, finalMinute;

                finalHour = "" + selectedHour;
                finalMinute = "" + selectedMinute;
                if (selectedHour < 10) finalHour = "0" + selectedHour;
                if (selectedMinute < 10) finalMinute = "0" + selectedMinute;
                finTime.setText(finalHour + ":" + finalMinute);

                Calendar today = Calendar.getInstance();

                today.set(Calendar.HOUR_OF_DAY, selectedHour);
                today.set(Calendar.MINUTE, selectedMinute);
                today.set(Calendar.SECOND, 0);

                SharedPreferences.Editor edit = settings.edit();
                edit.putString("Fin_hour", finalHour);
                edit.putString("Fin_minute", finalMinute);



                edit.commit();

                Toast.makeText(registro_hora.this, getString(R.string.changed_to_f, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();
                SystemClock.sleep(100);
                int i_hour=Integer.parseInt(settings.getString("Ini_hour",""));
                int i_minute=Integer.parseInt(settings.getString("Ini_minute",""));

                Calendar diff = today;

                diff.add(Calendar.HOUR_OF_DAY, -1*i_hour);
                diff.add(Calendar.MINUTE, -1*i_minute);
                diff.add(Calendar.SECOND, 0);

                sleep_h = diff.get(Calendar.HOUR_OF_DAY);
                sleep_m = diff.get(Calendar.MINUTE);


                Toast.makeText(registro_hora.this, getString(R.string.sleep_total, String.valueOf(sleep_h), String.valueOf(sleep_m)), Toast.LENGTH_LONG).show();
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePickerFinal.setTitle(getString(R.string.select_time));
        mTimePickerFinal.show();
    }

    private void aceptar(){
        HoraDiaria hora = HoraDiaria.getHora(settings);
        try {
            date = Calendar.getInstance();

            if(finTime.getText().toString().equals("")){
                Toast.makeText(registro_hora.this, "Por favor, selecciona la hora a la que te levantaste.", Toast.LENGTH_LONG).show();
            }else if(!settings.contains("diary_set")){
                String dataCompleta = informacion();
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("diary_set", ""+date.get(Calendar.DAY_OF_YEAR));
                edit.commit();
                hora.addHora(settings, date.get(Calendar.DAY_OF_WEEK), sleep_h);

                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                archivo.write(dataCompleta + iniTime.getText().toString() + "/" + finTime.getText().toString() + "/" + sleep_h + "/" + sleep_m + "/"
                        + date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + "/" + (date.get(Calendar.DAY_OF_MONTH)-1));
                archivo.flush();
                archivo.close();
                Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_LONG).show();
            }else if(settings.getString("diary_set","").equals(String.valueOf(date.get(Calendar.DAY_OF_YEAR)))){
                Toast.makeText(registro_hora.this, "Ya se ha realizado un registro el dia de hoy.", Toast.LENGTH_LONG).show();
            }else if(!settings.getString("diary_set","").equals(String.valueOf(date.get(Calendar.DAY_OF_YEAR)))){
                String dataCompleta = informacion();
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("diary_set", ""+date.get(Calendar.DAY_OF_YEAR));
                edit.commit();
                hora.addHora(settings, date.get(Calendar.DAY_OF_WEEK), sleep_h);
                OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                archivo.write(dataCompleta + iniTime.getText().toString() + "/" + finTime.getText().toString() + "/" + sleep_h + "/" + sleep_m + "/"
                        + date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + "/" + (date.get(Calendar.DAY_OF_MONTH)-1));
                archivo.flush();
                archivo.close();
                Toast.makeText(this, "Los datos se guardaron correctamente", Toast.LENGTH_LONG).show();
            }
        }catch (IOException e){

        }

        ble = this.getIntent().getExtras();
        String user = ble.getString("user");
        String passwd = ble.getString("passwd");
        Intent intent = new Intent(this, Main_Activity_Sueno.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("user",ble.getString("user"));
        intent.putExtra("passwd",ble.getString("passwd"));
        startActivity(intent);
    }

    private String informacion(){
        String archivos [] = fileList();
        String dataCompleta = "";

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                InputStreamReader archivo = new InputStreamReader(openFileInput("horas_dormidas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while(linea != null){
                    dataCompleta += linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                archivo.close();

            }catch (IOException e){

            }
        }

        return dataCompleta;
    }

    private boolean archivoExiste(String archivos [], String nombre_archivo){
        for(int i = 0; i < archivos.length; i++)
            if(archivos[i].equalsIgnoreCase(nombre_archivo))
                return true;
        return false;
    }

}