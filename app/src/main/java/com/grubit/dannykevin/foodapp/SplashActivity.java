package com.grubit.dannykevin.foodapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.dannyeng.foodselect.R;


public class SplashActivity extends ActionBarActivity {

    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 3;    // Sleep for some time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar

        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar


        setContentView(R.layout.activity_splash);


        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }


    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
