package com.example.myintentservice;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class myIntentService extends IntentService {


    public static final String CHANNEL_ID ="exampleServiceChannel";
    private PowerManager.WakeLock wakeLock;

    public myIntentService() {
        super("ExampleIntentService");

        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("intent", "Oncrete()");
        Toast.makeText(getApplicationContext(),"Service Started",Toast.LENGTH_LONG).show();

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "ExampleApp:wakelock");
        wakeLock.acquire();
        Log.d("intent", "wakelock acquire");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification noti = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("my Intentservice")
                    .setContentText("started")
                    .setSmallIcon(R.drawable.ic_baseline_alarm_add_24)
                    .build();

            startForeground(1,noti);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String input = intent.getStringExtra("sasken");
        for (int i = 0; i < 10; i++) {
            Log.d("intent", input + "->" + i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakeLock.release();
    }
}

