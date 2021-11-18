package com.deltasystem.quietness.alarma;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.deltasystem.quietness.R;


public class TimeChangeServiceClass extends IntentService {
    public TimeChangeServiceClass(String name) {
        super(name);
    }

    public TimeChangeServiceClass() {
        super("TimeChangeServiceClass");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString("Time");
        if (intentType == null) return;
        if (intentType.equals("TimeChangeReceiver")) {
            SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            Utils.setAlarm(settings.getInt("alarmID", 0), settings.getString("sound",""),settings.getLong("alarmTime", 0), this);
        }
    }
}
