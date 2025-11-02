package com.lms.LMS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Library {
    private String id;
    private String name;
    private String address;
    private List<ReadableItems> readableItems = new ArrayList<>();

    //new props
    private String phoneNumber;
    private String email;


    // Custom constructor for backward compatibility
    public Library(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.readableItems = new ArrayList<>();
    }

    // Keep the custom method
    public void addReadableItem(ReadableItems item) {
        this.readableItems.add(item);
    }
}