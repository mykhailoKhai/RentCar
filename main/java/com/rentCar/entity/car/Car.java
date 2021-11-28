package com.rentCar.entity.car;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {
    private long carId;
    private String carType;
    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private int yearCreation;
    private String transmissionType;
    private double engineSize;
    private int enginePower;
    private String fuelType;
    private double price;
    private boolean isActive;

    public Car() {
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYearCreation() {
        return yearCreation;
    }

    public void setYearCreation(int yearCreation) {
        this.yearCreation = yearCreation;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carType='" + carType + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", color='" + color + '\'' +
                ", yearCreation=" + yearCreation +
                ", transmissionType='" + transmissionType + '\'' +
                ", engineSize=" + engineSize +
                ", enginePower=" + enginePower +
                ", fuelType='" + fuelType + '\'' +
                ", price=" + price +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Car )) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                yearCreation == car.yearCreation &&
                Double.compare(car.engineSize, engineSize) == 0 &&
                enginePower == car.enginePower &&
                Double.compare(car.price, price) == 0 &&
                isActive == car.isActive &&
                Objects.equals(carType, car.carType) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(bodyType, car.bodyType) &&
                Objects.equals(color, car.color) &&
                Objects.equals(transmissionType, car.transmissionType) &&
                Objects.equals(fuelType, car.fuelType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carType, brand, model, bodyType, color, yearCreation, transmissionType, engineSize, enginePower, fuelType, price, isActive);
    }
}
