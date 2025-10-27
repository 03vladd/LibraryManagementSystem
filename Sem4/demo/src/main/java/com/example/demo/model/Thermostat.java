package com.example.demo.model;

public class Thermostat extends Device {
    private double temperature;

    public Thermostat(Integer id, String name, Boolean isOn, double temperature) {
        super(id, name, isOn);
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

}
