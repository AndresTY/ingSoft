package com.deltasystem.quietness.sueno;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.deltasystem.quietness.EstadisticaDiaria.HoraDiaria;
import com.deltasystem.quietness.R;
import com.deltasystem.quietness.update.sendInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class registro_hora{
    private static registro_hora r = null;

    private Context ctx;
    private SharedPreferences settings;
    private Bundle ble;

    private Calendar date;
    private int sleep_h = 0;
    private int sleep_m = 0;

    private registro_hora(Context ctx, SharedPreferences settings, Bundle ble) {
        this.ctx=ctx;
        this.settings = settings;
        this.ble=ble;
    }
    public static registro_hora getRegistro(Context ctx, SharedPreferences settings, Bundle ble) {
        if (r == null) {
            r = new registro_hora(ctx,settings,ble);
        }
        return r;
    }



    public void fecha_inicial(TextView iniTime){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePickerInicial;
        mTimePickerInicial = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
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

                Toast.makeText(ctx, ctx.getString(R.string.changed_to, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePickerInicial.setTitle(ctx.getString(R.string.select_time));
        mTimePickerInicial.show();
    }

    public void fecha_final(TextView finTime){

            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePickerFinal;
            mTimePickerFinal = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
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

                    Toast.makeText(ctx, ctx.getString(R.string.changed_to_f, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();
                    SystemClock.sleep(100);
                    int i_hour=Integer.parseInt(settings.getString("Ini_hour",""));
                    int i_minute=Integer.parseInt(settings.getString("Ini_minute",""));

                    Calendar diff = today;

                    diff.add(Calendar.HOUR_OF_DAY, -1*i_hour);
                    diff.add(Calendar.MINUTE, -1*i_minute);
                    diff.add(Calendar.SECOND, 0);

                    sleep_h = diff.get(Calendar.HOUR_OF_DAY);
                    sleep_m = diff.get(Calendar.MINUTE);



                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePickerFinal.setTitle(ctx.getString(R.string.select_time));
            mTimePickerFinal.show();


    }

    public void aceptar(TextView iniTime, TextView finTime){

        if(iniTime.getText().equals("--:--")){
            Toast.makeText(ctx,"Por favor, selecciona la hora a la que te dormiste" , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ctx, ctx.getString(R.string.sleep_total, String.valueOf(sleep_h), String.valueOf(sleep_m)), Toast.LENGTH_LONG).show();
            HoraDiaria hora = HoraDiaria.getHora(ctx,settings);
            try {
                date = Calendar.getInstance();

                if(finTime.getText().toString().equals("")){
                    Toast.makeText(ctx, "Por favor, selecciona la hora a la que te levantaste", Toast.LENGTH_LONG).show();
                }else if(!settings.contains("diary_set")){
                    String dataCompleta = informacion();
                    SharedPreferences.Editor edit = settings.edit();
                    edit.putString("diary_set", ""+date.get(Calendar.DAY_OF_YEAR));
                    edit.commit();
                    hora.addHora(settings, date.get(Calendar.DAY_OF_WEEK), sleep_h);

                    OutputStreamWriter archivo = new OutputStreamWriter(ctx.openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                    archivo.write(dataCompleta + iniTime.getText().toString() + "/" + finTime.getText().toString() + "/" + sleep_h + "/" + sleep_m + "/"
                            + date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + "/" + (date.get(Calendar.DAY_OF_MONTH)-1));
                    archivo.flush();
                    archivo.close();
                    cargar_sleep_register();
                    Toast.makeText(ctx, "Los datos se guardaron correctamente", Toast.LENGTH_LONG).show();
                }else if(settings.getString("diary_set","").equals(String.valueOf(date.get(Calendar.DAY_OF_YEAR)))){
                    Toast.makeText(ctx, "Ya se ha realizado un registro el dia de hoy.", Toast.LENGTH_LONG).show();
                }else if(!settings.getString("diary_set","").equals(String.valueOf(date.get(Calendar.DAY_OF_YEAR)))){
                    String dataCompleta = informacion();
                    SharedPreferences.Editor edit = settings.edit();
                    edit.putString("diary_set", ""+date.get(Calendar.DAY_OF_YEAR));
                    edit.commit();
                    hora.addHora(settings, date.get(Calendar.DAY_OF_WEEK), sleep_h);
                    OutputStreamWriter archivo = new OutputStreamWriter(ctx.openFileOutput("horas_dormidas.txt", Activity.MODE_PRIVATE));
                    archivo.write(dataCompleta + iniTime.getText().toString() + "/" + finTime.getText().toString() + "/" + sleep_h + "/" + sleep_m + "/"
                            + date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + "/" + (date.get(Calendar.DAY_OF_MONTH)-1));
                    archivo.flush();
                    archivo.close();
                    cargar_sleep_register();
                    Toast.makeText(ctx, "Los datos se guardaron correctamente", Toast.LENGTH_LONG).show();
                }
            }catch (IOException e){

            }
        }

    }

    private String informacion(){
        String archivos [] = ctx.fileList();
        String dataCompleta = "";

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                InputStreamReader archivo = new InputStreamReader(ctx.openFileInput("horas_dormidas.txt"));
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
    public void cargar_sleep_register(){
        String data = informacion();
        System.out.println(">>>INFORMACION");
        sendInfo si = new sendInfo();
        si.add_sueno(ble.getString("user"), ble.getString("passwd"), data);
        System.out.println(">>>CARGAR");
        System.out.println(data);
    }
}