package com.example.oderapp.model;

public class Address   {
    private int id;
    private String diachi;

    public Address( String diachi) {
        this.diachi = diachi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
