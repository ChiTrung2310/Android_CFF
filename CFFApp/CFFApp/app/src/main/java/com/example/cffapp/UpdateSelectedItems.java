package com.example.cffapp;

import java.util.ArrayList;

public interface UpdateSelectedItems {
    void addItems(String name, float price,int soluong, int image);
    void removeItem(String name, float price,int soluong, int image);
    void updateItem(String name, float price,int soluong, int vitri);

    ArrayList<OrderListModel> getItems();
}
