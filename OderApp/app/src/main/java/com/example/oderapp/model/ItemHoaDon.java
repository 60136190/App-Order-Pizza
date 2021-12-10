package com.example.oderapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "hoadonn")
public class ItemHoaDon implements Serializable {
        @PrimaryKey(autoGenerate = true)
        private int id;
        private String name;
        private String price;
        private String size;

        public ItemHoaDon(String name, String price, String size) {
            this.name = name;
            this.price = price;
            this.size = size;


        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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


    }