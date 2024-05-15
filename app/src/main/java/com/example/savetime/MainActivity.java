package com.example.savetime;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ScreenStateReceiver screenStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, ScreenCheckService.class);
        startService(intent);
        /*
        // Create the receiver
        screenStateReceiver = new ScreenStateReceiver();

        // Create the intent filter
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        // Register the receiver
        registerReceiver(screenStateReceiver, filter);

         */

    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver
        unregisterReceiver(screenStateReceiver);
    }
 */
}
