package com.vehicleRental.clientside.models;

import java.util.UUID;

public class Vehicle {

    private String id;
    private String brand;
    private String type;

    public Vehicle() {

    }

    public Vehicle(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
}
