package com.example.oderapp.model.request;

public class UserRequest {
    private String hoten;
    private String username;
    private String ngaysinh;
    private int gioitinh;
    private String dienthoai;

    public UserRequest() {
    }

    public UserRequest(String hoten, String username, String ngaysinh, int gioitinh, String dienthoai) {
        this.hoten = hoten;
        this.username = username;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.dienthoai = dienthoai;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }
}
