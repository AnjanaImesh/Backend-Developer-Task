package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a course in the system.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class course {
    private String id; // Changed from Long to String for Firebase compatibility
    private String name;
    private Double fee;
    private Long lecturerId;
    private String lecturerName;

    public course(String id, String name, Double fee, Long lecturerId) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.lecturerId = lecturerId;
    }
}
