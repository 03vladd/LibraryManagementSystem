package com.lms.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Publication {
    private String id;
    private String title;
    private List<ReadableItems> copies;
}