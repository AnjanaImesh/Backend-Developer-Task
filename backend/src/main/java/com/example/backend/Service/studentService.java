package com.example.backend.Service;

import com.example.backend.DTO.studentDTO;
import java.util.List;

public interface studentService {
    String saveStudent(studentDTO studentDTO);
    List<studentDTO> getAllStudents();
    studentDTO updateStudent(studentDTO studentDTO);
    String deleteStudent(Long id);
    studentDTO getStudentById(Long id);
    // Add more query methods as needed, e.g. by course, status, etc.
}
