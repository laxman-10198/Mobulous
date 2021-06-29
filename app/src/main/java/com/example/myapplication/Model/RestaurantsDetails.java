package com.example.myapplication.Model;

public class RestaurantsDetails {
    String name;
    String price;
    String address;
    String cuisines;

    public RestaurantsDetails() {
    }

    public RestaurantsDetails(String name, String price, String address, String cuisines) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.cuisines = cuisines;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }
}
