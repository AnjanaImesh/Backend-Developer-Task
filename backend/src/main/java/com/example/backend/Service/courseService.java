package com.example.backend.Service;

import com.example.backend.DTO.courseDTO;
import java.util.List;

public interface CourseService {
    String saveCourse(courseDTO courseDTO);
    List<courseDTO> getAllCourses();
    courseDTO updateCourse(courseDTO courseDTO);
    String deleteCourse(Long id);
    courseDTO getCourseById(Long id);
    // Add more query methods as needed, e.g. by lecturer, fee, etc.
}
