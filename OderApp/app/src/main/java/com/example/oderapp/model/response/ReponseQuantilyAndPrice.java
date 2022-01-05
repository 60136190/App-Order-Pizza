package com.example.oderapp.model.response;

import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.QuantilyAndPrice;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReponseQuantilyAndPrice {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<QuantilyAndPrice> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<QuantilyAndPrice> getData() {
        return data;
    }

    public void setData(List<QuantilyAndPrice> data) {
        this.data = data;
    }

}
