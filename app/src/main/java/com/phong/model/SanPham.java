package com.phong.model;

import androidx.annotation.NonNull;


public class SanPham {
    protected String id;
    protected String name;
    protected String price;

    public SanPham() {
    }

    public SanPham(String id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
