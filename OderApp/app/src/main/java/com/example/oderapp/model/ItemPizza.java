package com.example.oderapp.model;


import java.io.Serializable;

public class ItemPizza implements Serializable {
    private String tensp;
    private int gia;
    private String url;
    private String chitiet;
    private String size;


    public ItemPizza(String tensp, int gia, String url, String chitiet, String size) {
        this.tensp = tensp;
        this.gia = gia;
        this.url = url;
        this.chitiet = chitiet;
        this.size = size;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
