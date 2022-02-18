package com.example.cffapp;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

public class ProcessOrder extends FragmentActivity {


    View thongtin, diachi, xacnhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_order);
        thongtin = findViewById(R.id.viewProfile);
        diachi = findViewById(R.id.viewViTri);
        xacnhan = findViewById(R.id.viewXacNhan);

    }

}