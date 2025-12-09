package com.lms.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraries")
@Data
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Library name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ReadableItems> readableItems = new ArrayList<>();

    public Library(String name, String address) {
        this.name = name;
        this.address = address;
        this.readableItems = new ArrayList<>();
    }

    public void addReadableItem(ReadableItems item) {
        this.readableItems.add(item);
    }
}