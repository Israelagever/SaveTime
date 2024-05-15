package com.example.savetime;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ScreenCheckService extends Service {
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    private BroadcastReceiver screenStateReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create the BroadcastReceiver
        screenStateReceiver = new ScreenStateReceiver();

        // Create the intent filter

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        // Register the receiver
        registerReceiver(screenStateReceiver, filter);

        // Start the service in the foreground
        startForeground(NOTIF_ID, getNotification());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Unregister the receiver
        unregisterReceiver(screenStateReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification getNotification() {
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    NOTIF_CHANNEL_ID,
                    "Screen Check Service",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
        }
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

        return new NotificationCompat.Builder(this, NOTIF_CHANNEL_ID)
                .setContentTitle("Screen Check Service")
                .setContentText("Checking if screen is on...")
                .setSmallIcon(R.drawable.hourglass)
                .build();
    }
}
