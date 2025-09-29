package com.example.backend.Service.IMPL;

import com.example.backend.DTO.courseDTO;
import com.example.backend.Service.CourseService;
import com.example.backend.Repo.CourseRepo;
import com.example.backend.entity.course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class courseServiceIMPL implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public String saveCourse(courseDTO courseDTO) {
        try {
            course entity = new course();
            entity.setName(courseDTO.getName());
            entity.setFee(courseDTO.getFee());
            entity.setLecturerId(courseDTO.getLecturerId());
            entity.setLecturerName(courseDTO.getLecturerName());
            courseRepo.save(entity);
            return "Course saved successfully";
        } catch (Exception e) {
            return "Error saving course: " + e.getMessage();
        }
    }

    @Override
    public List<courseDTO> getAllCourses() {
        List<course> courses = courseRepo.findAll();
        return courses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public courseDTO updateCourse(courseDTO courseDTO) {
        Optional<course> existingCourse = courseRepo.findById(courseDTO.getId());
        if (existingCourse.isPresent()) {
            course entity = existingCourse.get();
            entity.setName(courseDTO.getName());
            entity.setFee(courseDTO.getFee());
            entity.setLecturerId(courseDTO.getLecturerId());
            entity.setLecturerName(courseDTO.getLecturerName());
            course updated = courseRepo.save(entity);
            return convertToDTO(updated);
        } else {
            throw new RuntimeException("Course not found with id: " + courseDTO.getId());
        }
    }

    @Override
    public String deleteCourse(Long id) {
        try {
            if (courseRepo.existsById(id)) {
                courseRepo.deleteById(id);
                return "Course deleted successfully";
            } else {
                return "Course not found with id: " + id;
            }
        } catch (Exception e) {
            return "Error deleting course: " + e.getMessage();
        }
    }

    @Override
    public courseDTO getCourseById(Long id) {
        Optional<course> courseOpt = courseRepo.findById(id);
        if (courseOpt.isPresent()) {
            return convertToDTO(courseOpt.get());
        } else {
            throw new RuntimeException("Course not found with id: " + id);
        }
    }

    // Helper method to convert Entity to DTO
    private courseDTO convertToDTO(course entity) {
        courseDTO dto = new courseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFee(entity.getFee());
        dto.setLecturerId(entity.getLecturerId());
        dto.setLecturerName(entity.getLecturerName());
        return dto;
    }
}
