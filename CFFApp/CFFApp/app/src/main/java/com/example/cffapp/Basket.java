package com.example.cffapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Basket extends AppCompatActivity {

    RecyclerView orderRv;
    ComfirmOrderActivityAdapter comfirmOrderActivityAdapter;
    Activity context;
    ArrayList<OrderListModel> orderListModel = ComfirmOrderActivityAdapter.orderListModels;
    Button datHang;
    TextView tongtien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        Toolbar toolbar = findViewById(R.id.toobar_basket);
        toolbar.setTitle("Giỏi hàng của bạn");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //finish();
            }
        });

            orderRv = findViewById(R.id.rv_order);
            comfirmOrderActivityAdapter = new ComfirmOrderActivityAdapter(context);
            orderRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            orderRv.setAdapter(comfirmOrderActivityAdapter);
            datHang = findViewById(R.id.order);
            datHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Basket.this,ProcessOrder.class);
                    startActivity(intent);
                }
            });
    }
}