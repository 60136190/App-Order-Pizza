package com.example.oderapp.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int image;
    private String name;
    private String price;
    private String size;
    private String detail;

    public Item(int image, String name, String price, String size, String detail ) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.size = size;
        this.detail = detail;
    }



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}