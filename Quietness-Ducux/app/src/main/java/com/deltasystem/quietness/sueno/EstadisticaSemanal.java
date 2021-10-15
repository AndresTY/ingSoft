package com.deltasystem.quietness.sueno;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class EstadisticaSemanal extends AppCompatActivity {

    private int [] total_sleep_h = new int[52];
    private int [] total_sleep_m = new int[52];
    private int [] counter = new int[52];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica_semanal);
        String archivos [] = fileList();

        if (archivoExiste(archivos, "horas_dormidas.txt")){
            try{

                InputStreamReader archivo = new InputStreamReader(openFileInput("horas_dormidas.txt"));
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

                imprimir();

            }catch (IOException e){

            }
        }
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

    private void imprimir(){

        Calendar tempA = Calendar.getInstance();
        tempA.set(tempA.get(Calendar.YEAR),tempA.get(Calendar.MONTH), 1);
        int semanaIni = buscarSemana(tempA);

        int aux = semanaIni;

        tempA.set(tempA.get(Calendar.YEAR),tempA.get(Calendar.MONTH), Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
        int semanaFin = buscarSemana(tempA);

        String reporte = "Semana.\t\t\t\tPromedio.\n";
        for(int i = 0; i <= (semanaFin - semanaIni); i++){
            reporte += "[" + (i + 1) + "]\t\t\t" + total_sleep_h[aux] + " h - " + total_sleep_m[aux] + " min \n";
            aux++;
        }

        TextView tv = (TextView) findViewById(R.id.Inicialtime);
        tv.setText(reporte);
    }
}