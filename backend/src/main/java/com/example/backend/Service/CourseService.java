package com.example.backend.Service;

import com.example.backend.DTO.courseDTO;
import java.util.List;

public interface CourseService {
    String saveCourse(courseDTO courseDTO);
    List<courseDTO> getAllCourses();
    courseDTO updateCourse(courseDTO courseDTO);
    String deleteCourse(String id);
    courseDTO getCourseById(String id);
    // Add more query methods as needed, e.g. by lecturer, fee, etc.
}
