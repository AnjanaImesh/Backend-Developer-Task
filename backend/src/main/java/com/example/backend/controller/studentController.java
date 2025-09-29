package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.DTO.studentDTO;
import com.example.backend.Service.studentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class studentController {

    @Autowired
    private studentService studentService;

    @PostMapping(path = "/save")
    public String save(@RequestBody studentDTO studentDTO) {
        return studentService.saveStudent(studentDTO);
    }

    @GetMapping(path = "/get-all")
    public List<studentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<studentDTO> updateStudent(
            @PathVariable String id,
            @RequestBody studentDTO studentDTO
    ) {
        studentDTO.setId(id);
        studentDTO updatedStudent = studentService.updateStudent(studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        String message = studentService.deleteStudent(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<studentDTO> getStudentById(@PathVariable String id) {
        studentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
}
