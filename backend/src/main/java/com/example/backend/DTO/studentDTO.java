package com.example.backend.DTO;

import com.example.backend.entity.student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for student entity.
 * Used for transferring student data between layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class studentDTO {
    private String id; // Changed from Long to String for Firebase compatibility
    private String title;
    private String name;
    private String address;
    private String city;
    private String course;

    /**
     * Constructor for creating DTO without ID (for new students).
     */
    public studentDTO(String title, String name, String address, String city, String course) {
        this.title = title;
        this.name = name;
        this.address = address;
        this.city = city;
        this.course = course;
    }

    /**
     * Convert Entity to DTO.
     */
    public static studentDTO fromEntity(student studentEntity) {
        if (studentEntity == null) {
            return null;
        }
        return new studentDTO(
            studentEntity.getId() != null ? studentEntity.getId().toString() : null,
            studentEntity.getTitle(),
            studentEntity.getName(),
            studentEntity.getAddress(),
            studentEntity.getCity(),
            studentEntity.getCourse()
        );
    }

    /**
     * Convert DTO to Entity.
     */
    public student toEntity() {
        student studentEntity = new student();
        studentEntity.setId(String.valueOf(this.id != null ? Long.parseLong(this.id) : null));
        studentEntity.setTitle(this.title);
        studentEntity.setName(this.name);
        studentEntity.setAddress(this.address);
        studentEntity.setCity(this.city);
        studentEntity.setCourse(this.course);
        return studentEntity;
    }
}
