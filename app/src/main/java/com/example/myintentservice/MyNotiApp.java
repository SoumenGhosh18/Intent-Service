package com.example.myintentservice;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyNotiApp extends Application {

    public static final String CHANNEL_ID ="myservice";

    @Override
    public void onCreate() {
        super.onCreate();

        createMyNotification();
    }

    private void createMyNotification() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID,"my service",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

        }
    }
}