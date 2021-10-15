package com.deltasystem.quietness.EstadisticaDiaria;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.deltasystem.quietness.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraficoHora extends AppCompatActivity {

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.barchart);

        chart.getDescription().setEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(true);
        chart.setPinchZoom(false);

        ArrayList<String> year = new ArrayList<>();

        year.add("Dom");
        year.add("Lun");
        year.add("Mar");
        year.add("Mie");
        year.add("Jue");
        year.add("Vie");
        year.add("Sab");
        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        HoraDiaria hora = HoraDiaria.getHora(settings);


        BarDataSet bardataset = new BarDataSet(hora.getHoras(), "Cantidad de horas");
        chart.animateY(5000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);

        XAxis xAxis = chart.getXAxis();
        xAxis.setLabelCount(year.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(year));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(7);
        xAxis.setCenterAxisLabels(false);
        chart.setFitBars(true);
        chart.invalidate();
    }
}

