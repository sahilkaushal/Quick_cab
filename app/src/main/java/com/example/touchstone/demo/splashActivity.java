package com.example.touchstone.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.VideoView;

public class splashActivity extends AppCompatActivity {

    NetworkInfo activeNetwork;
    RotateLoading rotateLoading;
    VideoView vidHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_splash);

        if (connectedToInternet()) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    boolean value = sharedPreferences.getBoolean("value", false);

                    if (value == true) {
                        Intent intent = new Intent(splashActivity.this, Navigation.class);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(splashActivity.this, Home.class);
                        startActivity(intent);

                    }

                    finish();

                }
            }, 4000);
            try {

                rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
                rotateLoading.start();

            } catch (Exception ex) {

            }

        } else
            Toast.makeText(splashActivity.this, "not Connected to Internet", Toast.LENGTH_SHORT).show();


    }


    Boolean connectedToInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        activeNetwork = cm.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());

    }

}
