package com.example.cffapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Variables
    Animation topAn,bottomAn;
    ImageView hinh;
    TextView cff, slog;

    private static  int SPLASH_SCREEN = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topAn = AnimationUtils.loadAnimation(this,R.anim.top_amintion);
        bottomAn = AnimationUtils.loadAnimation(this,R.anim.bottom_amination);

        //Hooks
        hinh = (ImageView) findViewById(R.id.hinh);
        cff = (TextView) findViewById(R.id.cff);
        slog = (TextView) findViewById(R.id.solgan);

        hinh.setAnimation(topAn);
        cff.setAnimation(bottomAn);
        slog.setAnimation(bottomAn);
        if(isConneted(this)) {
            // nhảy qua dashboard
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_SCREEN);
        }else showCustomDialog();
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Please connect to internet!").setCancelable(false)
            .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity( new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                startActivity( new Intent(getApplicationContext(),Re));
                System.exit(0);
            }
        });
    }

    private boolean isConneted(MainActivity mainActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi != null && wifi.isConnected() || (mobile != null && mobile.isConnected()))
            return true;
        return false;
    }
}