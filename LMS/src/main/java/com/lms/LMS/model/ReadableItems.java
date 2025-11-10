package com.lms.LMS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadableItems {
    private String id;
    private String title;
    private String barcode;
    private ReadableItemStatus status;
}