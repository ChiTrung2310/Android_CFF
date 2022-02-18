package com.example.cffapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class StaticRvAdapter  extends  RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{

    public StaticRvAdapter(ArrayList<StaticRvModel> items,Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    //khai bao mang voi static rc view Hodlder
    private ArrayList<StaticRvModel> items;
    int row_index = 0;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;
    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //lay layout tu parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_layout,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return  staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if(check){
            ArrayList<DymanicRVModel> items = SelecteMonAn.chicken();

            updateRecyclerView.callback(position,items);
            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
// add items mon an
                if(position == 0){
                        ArrayList<DymanicRVModel> items = SelecteMonAn.chicken();

                        updateRecyclerView.callback(position,items);
                }else  if (position == 1){

                    ArrayList<DymanicRVModel> items =SelecteMonAn.burger();

                    updateRecyclerView.callback(position,items);
                }else  if (position == 2){

                    ArrayList<DymanicRVModel> items = SelecteMonAn.drink();

                    updateRecyclerView.callback(position,items);
                }else  if (position == 3){

                    ArrayList<DymanicRVModel> items = SelecteMonAn.combo();

                    updateRecyclerView.callback(position,items);
                }else  if (position == 4){

                    ArrayList<DymanicRVModel> items = SelecteMonAn.fries();

                    updateRecyclerView.callback(position,items);
                }

            }
        });

        if(select){
            if(position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            select = false;
        }else {
            if(row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected);
            }

        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;
        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imagestatic);
            textView = (TextView) itemView.findViewById(R.id.loaidoan);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayour);
        }
    }
}
