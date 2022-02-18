package com.example.cffapp;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;

public class ApplicationMain extends Application implements UpdateSelectedItems {
    private static Context context;
    public static ArrayList<OrderListModel> orderListModels;
    @Override
    public void addItems(String name, float price,int soluong, int image) {
        orderListModels.add( new OrderListModel(name, price,soluong, image));
    }
    public void removeItem(String name, float price,int soluong, int image){
        OrderListModel odrm = new OrderListModel(name,price,soluong ,image);
        /**only when you are not iterating over ArrayList if you are iterating then use Iterator.remove() method,
         * failing to do so may result in ConcurrentModificationException in Java
        */
        Iterator<OrderListModel> itr = orderListModels.iterator();
        while (itr.hasNext()){
            OrderListModel x = (OrderListModel) itr.next();
            if(x.getName().equals(odrm.getName()))
                itr.remove();
        }
    }

    @Override
    public void updateItem(String name, float price, int soluong, int vitri) {
        orderListModels.get(vitri).setSoluong(soluong);
    }

    @Override
    public ArrayList<OrderListModel> getItems() {
        return orderListModels;
    }

    public static Context getMyContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        orderListModels = new ArrayList<>();
    }

}
