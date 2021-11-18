package com.deltasystem.quietness.sueno;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class EstadisticaSemanal{
    private static EstadisticaSemanal est = null;

    private Context ctx;
    private int [] total_sleep_h = new int[52];
    private int [] total_sleep_m = new int[52];
    private int [] counter = new int[52];
    private String[] rango_semanas= new String[53];

    private EstadisticaSemanal(Context ctx) {
        this.ctx=ctx;
    }
    public static EstadisticaSemanal getRegistro(Context ctx) {
        if (est == null) {
            est = new EstadisticaSemanal(ctx);
        }
        return est;
    }

    private int buscarSemana(Calendar c){
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    private boolean archivoExiste(String archivos [], String nombre_archivo){
        for(int i = 0; i < archivos.length; i++)
            if(archivos[i].equalsIgnoreCase(nombre_archivo))
                return true;
        return false;
    }

    public void imprimir(int i, TextView num,TextView rango, TextView prom){

        Calendar tempA = Calendar.getInstance();
        tempA.set(Calendar.DAY_OF_MONTH, 1);
        tempA.add(Calendar.DAY_OF_WEEK, i*(7-tempA.get(Calendar.DAY_OF_WEEK)));

        int semana = buscarSemana(tempA);

        String rango_sem = rango_semanas[semana];
        rango.setText(rango_sem);

        String numero = String.valueOf(i);
        num.setText("\t"+numero);

        if(total_sleep_h[semana]!=0 && total_sleep_m[semana]>0){
            String promedio = total_sleep_h[semana]+":"+total_sleep_m[semana];
            prom.setText(promedio);
        }else{
            prom.setText("--:--");
        }
    }

    public void iniciar_promedios(){
        String archivos [] = ctx.fileList();

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                InputStreamReader archivo = new InputStreamReader(ctx.openFileInput("horas_dormidas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                System.out.println(linea);
                boolean flag = false;

                while(linea != null){

                    if(flag){
                        String[] splitted = linea.split("/");

                        Calendar tempC = Calendar.getInstance();

                        tempC.set(Integer.parseInt(splitted[4]),Integer.parseInt(splitted[5]),Integer.parseInt(splitted[6]));
                        int semana = buscarSemana(tempC);

                        total_sleep_m [semana] += Integer.parseInt(splitted[3]);
                        total_sleep_h [semana] += Integer.parseInt(splitted[2]);
                        linea = br.readLine();
                        counter[semana]++;
                    }else{
                        flag = true;
                    }
                }

                br.close();
                archivo.close();

                for (int i = 0; i < counter.length; i++){
                    if(counter[i] != 0){
                        total_sleep_m [i] /= counter[i];
                        total_sleep_h [i] /= counter[i];
                    }
                }


            }catch (IOException e){

            }
        }
    }

    public void iniciar_rangos(){
        String in_dia="";
        String fin_dia="";
        int aux=0;
        Calendar tempC = Calendar.getInstance();
        tempC.set(Calendar.MONTH, 0);

        boolean atras=false;
        for(int i=0;i<=11;i++) {


            if(i!=0) {
                if(!atras) {
                    tempC.set(Calendar.MONTH, i);
                }

            }



            //1
            if(!atras) {
                tempC.set(Calendar.DAY_OF_MONTH, 1);
            }else {
                atras=false;
            }


            in_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));
            aux=buscarSemana(tempC);
            tempC.add(Calendar.DAY_OF_WEEK, (7-tempC.get(Calendar.DAY_OF_WEEK)));
            fin_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));

            rango_semanas[aux]="Del "+in_dia+" al "+fin_dia;

            //2
            tempC.add(Calendar.DAY_OF_WEEK, (8-tempC.get(Calendar.DAY_OF_WEEK)));
            in_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));
            aux=buscarSemana(tempC);
            tempC.add(Calendar.DAY_OF_WEEK, (7-tempC.get(Calendar.DAY_OF_WEEK)));
            fin_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));

            rango_semanas[aux]="Del "+in_dia+" al "+fin_dia;


            //3
            tempC.add(Calendar.DAY_OF_WEEK, (8-tempC.get(Calendar.DAY_OF_WEEK)));
            in_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));
            aux=buscarSemana(tempC);
            tempC.add(Calendar.DAY_OF_WEEK, (7-tempC.get(Calendar.DAY_OF_WEEK)));
            fin_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));

            rango_semanas[aux]="Del "+in_dia+" al "+fin_dia;


            //4
            tempC.add(Calendar.DAY_OF_WEEK, (8-tempC.get(Calendar.DAY_OF_WEEK)));
            in_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));
            aux=buscarSemana(tempC);
            tempC.add(Calendar.DAY_OF_WEEK, (7-tempC.get(Calendar.DAY_OF_WEEK)));
            fin_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));

            rango_semanas[aux]="Del "+in_dia+" al "+fin_dia;


            //5
            tempC.add(Calendar.DAY_OF_WEEK, (8-tempC.get(Calendar.DAY_OF_WEEK)));
            in_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));
            aux=buscarSemana(tempC);
            tempC.add(Calendar.DAY_OF_WEEK, (7-tempC.get(Calendar.DAY_OF_WEEK)));
            fin_dia=String.valueOf(tempC.get(Calendar.DAY_OF_MONTH));

            if(i!=11) {
                rango_semanas[aux]="Del "+in_dia+" al "+fin_dia;
            }


            if(i!=0) {
                tempC.set(Calendar.MONTH, i);
            }

            tempC.set(Calendar.DAY_OF_MONTH, 1);
            tempC.set(Calendar.DAY_OF_MONTH, tempC.getActualMaximum(Calendar.DATE));
            if(tempC.get(Calendar.DAY_OF_WEEK)!=7) {
                atras=true;
                if(tempC.get(Calendar.DAY_OF_WEEK)>1) {
                    tempC.add(Calendar.DAY_OF_MONTH, -1*(tempC.get(Calendar.DAY_OF_WEEK)-1));
                }
            }


        }
    }
}