package com.lms.LMS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readable_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadableItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Barcode cannot be blank")
    private String barcode;

    @Enumerated(EnumType.STRING)
    private ReadableItemStatus status;

    @ManyToOne
    @JoinColumn(name = "library_id")
    @ToString.Exclude
    private Library library;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    @ToString.Exclude
    private Loan loan;
}