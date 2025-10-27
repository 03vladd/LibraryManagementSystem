package com.example.demo.repository;

import com.example.demo.model.Device;
import com.example.demo.model.Light;
import com.example.demo.model.Thermostat;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DeviceRepository {
    private List<Device> devices = new ArrayList<>(Arrays.asList(
            new Light(1, "Light 1", false, 80.0),
            new Thermostat(2, "Thermostat 1", true, 22.5)
    ));

    public List<Device> findAll() {
        return devices;
    }

    public Device findById(Integer id) {
        for (Device device : devices) {
            if (device.getId().equals(id)) {
                return device;
            }
        }
        return null;
    }
}