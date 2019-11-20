package com.vehicleRental.clientside.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class VehicleForm {

    @NotNull
    @Size(min=1, max=30)
    private String brand;

    @NotNull
    @Size(min=1, max=30)
    private String type;

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

    public String toString() {
        return "Vehicle(brand: " + this.brand + ", type: " + this.type + ")";
    }
}
