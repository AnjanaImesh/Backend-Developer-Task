package com.example.backend.DTO;

import com.example.backend.entity.course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for course entity.
 * Used for transferring course data between layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class courseDTO {
    private Long id;
    private String name;
    private Double fee;
    private Long lecturerId;
    private String lecturerName;

    /**
     * Constructor for creating DTO without ID (for new courses).
     */
    public courseDTO(String name, Double fee, Long lecturerId, String lecturerName) {
        this.name = name;
        this.fee = fee;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    /**
     * Convert Entity to DTO.
     */
    public static courseDTO fromEntity(course courseEntity) {
        if (courseEntity == null) {
            return null;
        }
        return new courseDTO(
            courseEntity.getId(),
            courseEntity.getName(),
            courseEntity.getFee(),
            courseEntity.getLecturerId(),
            courseEntity.getLecturerName()
        );
    }

    /**
     * Convert DTO to Entity.
     */
    public course toEntity() {
        course courseEntity = new course();
        courseEntity.setId(this.id);
        courseEntity.setName(this.name);
        courseEntity.setFee(this.fee);
        courseEntity.setLecturerId(this.lecturerId);
        courseEntity.setLecturerName(this.lecturerName);
        return courseEntity;
    }
}
