package com.example.demo.service;

import com.example.demo.exception.DeviceNotFoundException;
import com.example.demo.model.Device;
import com.example.demo.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device findById(Integer id) throws Exception {
        Device device = deviceRepository.findById(id);

        if (device == null) {
            throw new DeviceNotFoundException(id);
        }
        return device;
    }
}