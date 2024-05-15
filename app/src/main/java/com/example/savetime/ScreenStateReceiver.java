package com.example.savetime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ScreenStateReceiver extends BroadcastReceiver {

    private static final long HALF_HOUR_IN_MILLIS = 30 * 60 * 1000;
    //private static final long HALF_HOUR_IN_MILLIS =20 * 1000;
    private CountDownTimer timer;

    private static final String CHANNEL_ID = "channel_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            startTimer(context);
        } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            cancelTimer();
        }
    }

    private void startTimer(final Context context) {
        timer = new CountDownTimer(HALF_HOUR_IN_MILLIS, HALF_HOUR_IN_MILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Do nothing
            }

            @Override
            public void onFinish() {
                createNotificationChannel(context);
            }
        }.start();
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }


    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.hourglass)
                .setContentTitle("תתעורר!!!")
                .setContentText("והנה ה' ניצב עליו ומלאו כל הארץ כבודו")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
        Toast.makeText(context, "עבר הזמן", Toast.LENGTH_SHORT).show();
    }

}
