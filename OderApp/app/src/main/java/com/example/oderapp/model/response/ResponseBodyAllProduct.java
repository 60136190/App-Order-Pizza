package com.example.oderapp.model.response;

import com.example.oderapp.model.ItemAllFood;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBodyAllProduct {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ItemAllFood> data = null;

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

    public List<ItemAllFood> getData() {
        return data;
    }

    public void setData(List<ItemAllFood> data) {
        this.data = data;
    }

}
