package com.lms.LMS.model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String id;
    private String name;
    private String address;
    private List<ReadableItems> readableItems;

    // NEW PROPERTIES ADDED
    private String phoneNumber;
    private String email;

    // Constructor
    public Library() {
        this.readableItems = new ArrayList<>();
    }

    public Library(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.readableItems = new ArrayList<>();
    }

    // Getters and Setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ReadableItems> getReadableItems() {
        return readableItems;
    }

    public void setReadableItems(List<ReadableItems> readableItems) {
        this.readableItems = readableItems;
    }

    public void addReadableItem(ReadableItems item) {
        this.readableItems.add(item);
    }

    // NEW GETTERS AND SETTERS
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
}
}