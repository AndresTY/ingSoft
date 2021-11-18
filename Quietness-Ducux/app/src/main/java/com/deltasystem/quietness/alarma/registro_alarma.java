package com.deltasystem.quietness.alarma;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.deltasystem.quietness.R;


import java.util.Calendar;

public class registro_alarma extends AppCompatActivity {

    private TextView notificationsTime;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sueno);
        notificationsTime = (TextView) findViewById(R.id.alarm_time);
        settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        set_alarma();
    }

    private int alarmID = 1;

    public void set_alarma() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String finalHour, finalMinute;

                finalHour = "" + selectedHour;
                finalMinute = "" + selectedMinute;
                if (selectedHour < 10) finalHour = "0" + selectedHour;
                if (selectedMinute < 10) finalMinute = "0" + selectedMinute;
                notificationsTime.setText(finalHour + ":" + finalMinute);

                Calendar today = Calendar.getInstance();

                today.set(Calendar.HOUR_OF_DAY, selectedHour);
                today.set(Calendar.MINUTE, selectedMinute);
                today.set(Calendar.SECOND, 0);

                SharedPreferences.Editor edit = settings.edit();
                edit.putString("hour", finalHour);
                edit.putString("minute", finalMinute);

                //SAVE ALARM TIME TO USE IT IN CASE OF REBOOT
                edit.putInt("alarmID", alarmID);
                edit.putLong("alarmTime", today.getTimeInMillis());
                edit.putString("sound",settings.getString("sound",""));
                edit.commit();

                Toast.makeText(registro_alarma.this, getString(R.string.changed_to, finalHour + ":" + finalMinute), Toast.LENGTH_LONG).show();

                Utils.setAlarm(alarmID, settings.getString("sound",""),today.getTimeInMillis(), registro_alarma.this);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle(getString(R.string.select_time));
        mTimePicker.show();

    }




}
