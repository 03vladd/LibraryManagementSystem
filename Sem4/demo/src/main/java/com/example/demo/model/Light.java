package com.example.demo.model;

public class Light extends Device {

    private double brightness;

    public Light(Integer id, String name, Boolean isOn, double brightness) {
        super(id, name, isOn);
        this.brightness = brightness;
    }
}
