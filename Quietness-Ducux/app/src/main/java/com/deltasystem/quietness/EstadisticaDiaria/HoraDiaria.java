package com.deltasystem.quietness.EstadisticaDiaria;

import android.content.SharedPreferences;

import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HoraDiaria {
    private static HoraDiaria h = null;
    private ArrayList<BarEntry> horas = new ArrayList<>();
    private HoraDiaria(SharedPreferences settings,int i){
        if(i==0){
            horas.add(new BarEntry(0, 0));
            horas.add(new BarEntry(1, 0));
            horas.add(new BarEntry(2, 0));
            horas.add(new BarEntry(3, 0));
            horas.add(new BarEntry(4, 0));
            horas.add(new BarEntry(5, 0));
            horas.add(new BarEntry(6, 0));
        }else{
            horas = getArrayList(settings);
        }

    }

    public static HoraDiaria getHora(SharedPreferences settings){

        if(h==null && !settings.contains("HorasGraf")){
            h= new HoraDiaria(settings,0);
        }else {
            if (!settings.contains("HorasGraf")) {
                return h;
            }
            h=new HoraDiaria(settings,1);
        }
        return h;
    }

    public void addHora(SharedPreferences settings,int dia, int hora){
        if(dia==1){
            horas.set(6,new BarEntry(6,hora));
        }else if(dia==3){
            reiniciar(settings);
            horas.set(dia-2,new BarEntry(dia-2,hora));
        }else{
            horas.set(dia-2,new BarEntry(dia-2,hora));
        }
        saveArrayList(settings);
    }

    public void saveArrayList(SharedPreferences settings){
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(horas);
        editor.putString("HorasGraf", json);
        editor.apply();

    }

    public ArrayList<BarEntry> getArrayList(SharedPreferences settings){
        Gson gson = new Gson();
        String json = settings.getString("HorasGraf", null);
        Type type = new TypeToken<ArrayList<BarEntry>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void reiniciar(SharedPreferences settings){
        h=new HoraDiaria(settings,0);
    }

    public ArrayList<BarEntry> getHoras() {
        return horas;
    }
}
