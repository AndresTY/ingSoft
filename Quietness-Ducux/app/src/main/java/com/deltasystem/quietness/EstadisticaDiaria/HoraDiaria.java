package com.deltasystem.quietness.EstadisticaDiaria;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class HoraDiaria {
    private static HoraDiaria h = null;
    private ArrayList<BarEntry> horas = new ArrayList<>();
    private Context ctx;
    private boolean finish = true;

    private HoraDiaria(Context ctx, SharedPreferences settings, int i) {
        this.ctx=ctx;
        if (i == 0) {
            initArray();
        } else if (i == 1) {
            horas = getArrayList(settings);
        } else {
            initArray();
            getArrayListFromTxt(settings, getHoursFromTxt(settings));
        }

    }

    public void initArray(){
        horas.add(new BarEntry(0, 0));
        horas.add(new BarEntry(1, 0));
        horas.add(new BarEntry(2, 0));
        horas.add(new BarEntry(3, 0));
        horas.add(new BarEntry(4, 0));
        horas.add(new BarEntry(5, 0));
        horas.add(new BarEntry(6, 0));
    }

    public static HoraDiaria getHora(Context ctx, SharedPreferences settings) {
        String text = settings.getString("Txt", "");
        if (h == null && !settings.contains("HorasGraf") && !text.equals("New")) {
            System.out.println(">>>Old");
            h = new HoraDiaria(ctx,settings, 0);
        } else {

            if (text.equals("New")) {
                System.out.println(">>>New");
                h = new HoraDiaria(ctx,settings, 2);
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("Txt", "Now");
                edit.commit();
            }
            if (settings.contains("HorasGraf")) {
                System.out.println(">>>Old(2)");
                h = new HoraDiaria(ctx,settings, 1);
            }
        }
        return h;
    }

    public void addHora(SharedPreferences settings, int dia, int hora) {
        System.out.println(horas.toString());
        System.out.println(horas.size());

        if (dia == 1) {
            horas.set(6, new BarEntry(6, hora));
        } else if (dia == 3) {
            if (finish) {
                reiniciar(ctx,settings);
            }
            horas.set(dia - 2, new BarEntry(dia - 2, hora));
        } else {
            horas.set(dia - 2, new BarEntry(dia - 2, hora));
        }
        saveArrayList(settings);
    }

    public void saveArrayList(SharedPreferences settings) {
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(horas);
        editor.putString("HorasGraf", json);
        editor.apply();
    }

    public ArrayList<BarEntry> getArrayList(SharedPreferences settings) {
        Gson gson = new Gson();
        String json = settings.getString("HorasGraf", null);
        Type type = new TypeToken<ArrayList<BarEntry>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    private void getArrayListFromTxt(SharedPreferences settings, int[] sleep) {
        finish = false;
        for (int i = 0; i < sleep.length; i++) {
            System.out.println("--"+i+", "+sleep[i]);
            if (i != 0) {

                addHora(settings, i, sleep[i]);
            } else {
                addHora(settings, 7, sleep[i]);
            }

        }
        finish = true;
    }

    private void reiniciar(Context ctx,SharedPreferences settings) {
        h = new HoraDiaria(ctx,settings, 0);
    }

    private int[] getHoursFromTxt(SharedPreferences settings) {
        int[] sleep_h_sem = new int[7];
        try {
            InputStreamReader archivo = new InputStreamReader(ctx.openFileInput("horas_dormidas.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            Calendar tempC = Calendar.getInstance();
            int dia = tempC.get(Calendar.DAY_OF_YEAR);
            int semana = buscarSemana(tempC);
            int aux = 0;
            boolean flag = false;

            while (linea != null) {

                if (flag) {
                    String[] splitted = linea.split("/");


                    tempC.set(Integer.parseInt(splitted[4]), Integer.parseInt(splitted[5]),
                            Integer.parseInt(splitted[6])+1);
                    if(tempC.get(Calendar.DAY_OF_YEAR)==dia){
                        SharedPreferences.Editor edit = settings.edit();
                        edit.putString("diary_set", ""+tempC.get(Calendar.DAY_OF_YEAR));
                        edit.commit();
                    }
                    aux = buscarSemana(tempC);
                    System.out.println(semana+","+aux);
                    if (semana == aux) {
                        if (tempC.get(Calendar.DAY_OF_WEEK) != 7) {
                            sleep_h_sem[tempC.get(Calendar.DAY_OF_WEEK)] = Integer.parseInt(splitted[2]);
                        } else {
                            sleep_h_sem[0] = Integer.parseInt(splitted[2]);
                        }

                    }
                    linea = br.readLine();
                } else {
                    flag = true;
                }
            }

            br.close();
            archivo.close();


        } catch (IOException e) {

        }
        System.out.println(">>>NewTxt");
        for (int i = 0; i < sleep_h_sem.length; i++) {
            System.out.println("--"+i+", "+sleep_h_sem[i]);
        }
        return sleep_h_sem;
    }

    private int buscarSemana(Calendar c) {
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    public ArrayList<BarEntry> getHoras() {
        return horas;
    }
}
