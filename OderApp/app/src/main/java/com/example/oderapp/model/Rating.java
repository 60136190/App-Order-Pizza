package com.example.oderapp.model;

import java.io.Serializable;

public class Rating implements Serializable {
    private int diem_danhgia;
    private String binhluan;

    public Rating( int diem_danhgia, String binhluan) {
        this.diem_danhgia = diem_danhgia;
        this.binhluan = binhluan;
    }


    public int getDiem_danhgia() {
        return diem_danhgia;
    }

    public void setDiem_danhgia(int diem_danhgia) {
        this.diem_danhgia = diem_danhgia;
    }

    public String getBinhluan() {
        return binhluan;
    }

    public void setBinhluan(String binhluan) {
        this.binhluan = binhluan;
    }
}
