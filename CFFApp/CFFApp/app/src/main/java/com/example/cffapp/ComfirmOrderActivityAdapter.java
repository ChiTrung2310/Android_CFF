package com.example.cffapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ComfirmOrderActivityAdapter extends RecyclerView.Adapter<ComfirmOrderActivityAdapter.ComfirmOrderViewHolder> {

    public static ArrayList<OrderListModel> orderListModels;
    Activity activity;
    private int soluong;
    private String ten_sp;
            float gia;

    public ComfirmOrderActivityAdapter(Activity activity){
        this.activity = activity;
        orderListModels = ((UpdateSelectedItems) ApplicationMain.getMyContext()).getItems();
    }


    @NonNull
    @Override
    public ComfirmOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.oder_item,parent,false);
        ComfirmOrderViewHolder comfirmOrderViewHolder = new ComfirmOrderViewHolder(view);
        return comfirmOrderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComfirmOrderViewHolder holder, int position) {
        OrderListModel currentItem = orderListModels.get(position);
        holder.name.setText(currentItem.getName());
        holder.imageView.setImageResource(currentItem.getImage());
        holder.price.setText(currentItem.getPrice()+"");
        holder.amount.setText(currentItem.getSoluong()+"");
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = Integer.parseInt(holder.amount.getText().toString());
                if(i>1) {
                    holder.amount.setText(i - 1 + "");
                    soluong = Integer.parseInt(holder.amount.getText().toString());
                    ((UpdateSelectedItems) ApplicationMain.getMyContext()).updateItem(ten_sp, gia, soluong, position);
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.amount.setText(Integer.parseInt(holder.amount.getText().toString())+1+"");
                soluong = Integer.parseInt(holder.amount.getText().toString());
                ((UpdateSelectedItems) ApplicationMain.getMyContext()).updateItem(ten_sp, gia, soluong, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }

    public  class ComfirmOrderViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, amount;
        ImageView imageView,plus, minus;
        public ComfirmOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_order);
            price = itemView.findViewById(R.id.gia_order);
            imageView = itemView.findViewById(R.id.image_order);
            amount = itemView.findViewById(R.id.soluong);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.subtract);
        }
    }
}
