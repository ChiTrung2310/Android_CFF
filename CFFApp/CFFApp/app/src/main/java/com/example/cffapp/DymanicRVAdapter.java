package com.example.cffapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DymanicRVAdapter extends RecyclerView.Adapter<DymanicRVAdapter.DymanicRvHolder>{

    public ArrayList<DymanicRVModel> dymanicRVModels;
    public ArrayList<OrderListModel> orderListModels = ApplicationMain.orderListModels;

    String ten_sp;
    float gia;
    int image, soluong;

    public DymanicRVAdapter(ArrayList<DymanicRVModel> dymanicRVModels, Context context) {
        this.dymanicRVModels = dymanicRVModels;
        this.context = context;
    }

    Context context;
    UpdateSelectedItems updateSelectedItems;
    public  DymanicRVAdapter(ArrayList<DymanicRVModel> dymanicRVModels,Context context,UpdateSelectedItems updateSelectedItems){
        this.dymanicRVModels = dymanicRVModels;
        this.context = context;
        this.updateSelectedItems = updateSelectedItems;
    }


    public class DymanicRvHolder extends RecyclerView.ViewHolder   {

        public ImageView imageView, add, check;
        public TextView name_tv, gia_tv;
        ConstraintLayout constraintLayout;


        public DymanicRvHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name_tv = itemView.findViewById(R.id.name_sp);
            gia_tv = itemView.findViewById(R.id.gia_sp);
            add = itemView.findViewById(R.id.add);
            check = itemView.findViewById(R.id.check);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
    @NonNull
    @Override
    public DymanicRVAdapter.DymanicRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.dymanic_rv_item_layout,parent,false);
        DymanicRvHolder dymanicRvHolder = new DymanicRvHolder(view);
        return  dymanicRvHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DymanicRvHolder holder, int position) {
        DymanicRVModel currentItem = dymanicRVModels.get(position);

        holder.imageView.setImageResource(currentItem.getImage());
        holder.name_tv.setText(currentItem.getName());
        holder.gia_tv.setText(currentItem.getPrice()+"");
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten_sp = currentItem.getName();
                gia = currentItem.getPrice();
                image = currentItem.getImage();
                soluong = 1;
                ((UpdateSelectedItems) ApplicationMain.getMyContext()).addItems(ten_sp,gia,soluong,image);
                holder.add.setVisibility(View.INVISIBLE);
                holder.check.setVisibility(View.VISIBLE);
            }
        });
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten_sp = currentItem.getName();
                gia = currentItem.getPrice();
                image = currentItem.getImage();
                soluong = 0;
                ((UpdateSelectedItems) ApplicationMain.getMyContext()).removeItem(ten_sp,gia,soluong,image);
                holder.add.setVisibility(View.VISIBLE);
                holder.check.setVisibility(View.INVISIBLE);
            }
        });
        if(orderListModels != null)
            for (OrderListModel od:orderListModels) {
                if(od.getName().equals(currentItem.name)){
                    holder.add.setVisibility(View.INVISIBLE);
                    holder.check.setVisibility(View.VISIBLE);
                }
            }
    }

    @Override
    public int getItemCount() {
        return dymanicRVModels.size();
    }


}