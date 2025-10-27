package com.example.demo.exception;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(Integer id) {
        super("There was no device found for the given ID: " + id);
    }
}
