package com.example.cffapp;

public class DymanicRVModel {

    String name;
    float price;
    private  int image;

    public DymanicRVModel(String name, float price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }
    public  float getPrice(){return price;}
}
