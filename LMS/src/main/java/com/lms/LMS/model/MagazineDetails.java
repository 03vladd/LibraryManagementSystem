package com.lms.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MAGAZINE")
@Data
@NoArgsConstructor
public class MagazineDetails extends Publication {
    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    public MagazineDetails(String title, List<ReadableItems> copies, String publisher) {
        super(null, title, copies);
        this.publisher = publisher;
    }
}