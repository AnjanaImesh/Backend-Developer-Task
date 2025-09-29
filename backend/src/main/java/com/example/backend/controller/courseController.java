package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.DTO.courseDTO;
import com.example.backend.Service.CourseService;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
@CrossOrigin
public class courseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(path = "/save")
    public String save(@RequestBody courseDTO courseDTO) {
        return courseService.saveCourse(courseDTO);
    }

    @GetMapping(path = "/get-all")
    public List<courseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<courseDTO> updateCourse(
            @PathVariable String id,
            @RequestBody courseDTO courseDTO
    ) {
        courseDTO.setId(id);
        courseDTO updatedCourse = courseService.updateCourse(courseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) {
        String message = courseService.deleteCourse(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<courseDTO> getCourseById(@PathVariable String id) {
        courseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }
}
