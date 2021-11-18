package com.deltasystem.quietness.alarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context, NotificationService.class);
        service1.putExtra("sound",intent.getExtras().getString("sound"));
        service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        ContextCompat.startForegroundService(context, service1 );
        Log.d("A", " ALARM RECEIVED!!!");
    }
}
