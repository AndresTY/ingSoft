package com.deltasystem.quietness.alarma;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Utils {
    public static void setAlarm(int i,String sound, Long timestamp, Context ctx) {
        AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(ALARM_SERVICE);
        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(ctx, i, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmIntent.putExtra("sound",sound);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timestamp, pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timestamp,AlarmManager.INTERVAL_DAY,pendingIntent);
    }
}
