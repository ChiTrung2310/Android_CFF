package com.example.cffapp;

public class OrderListModel {
    String name;
    float price;
    int image;

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    int soluong;

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
    public int getImage()
    {
    return image;
    }
    public OrderListModel(String name, float price,int soluong, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.soluong = soluong;
    }
}
