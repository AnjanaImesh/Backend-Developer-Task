package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a student in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class student {
    private String id; // Changed from Long to String for Firebase compatibility
    private String title;
    private String name;
    private String address;
    private String city;
    private String course;
}
