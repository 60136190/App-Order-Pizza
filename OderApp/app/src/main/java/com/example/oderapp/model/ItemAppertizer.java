package com.example.oderapp.model;

import java.io.Serializable;

public class ItemAppertizer implements Serializable {
    private String tensp;
    private int gia;
    private String hinhanh;
    private String size;

    public ItemAppertizer(String tensp, int gia, String hinhanh, String size) {
        this.tensp = tensp;
        this.gia = gia;
        this.hinhanh = hinhanh;
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

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
