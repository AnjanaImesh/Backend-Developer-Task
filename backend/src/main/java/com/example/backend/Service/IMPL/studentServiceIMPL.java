package com.example.backend.Service.IMPL;

import com.example.backend.DTO.studentDTO;
import com.example.backend.Service.studentService;
import com.example.backend.Repo.StudentRepo;
import com.example.backend.entity.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class studentServiceIMPL implements studentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    public String saveStudent(studentDTO studentDTO) {
        try {
            student entity = new student();
            entity.setTitle(studentDTO.getTitle());
            entity.setName(studentDTO.getName());
            entity.setAddress(studentDTO.getAddress());
            entity.setCity(studentDTO.getCity());
            entity.setCourse(studentDTO.getCourse());
            studentRepo.save(entity);
            return "Student saved successfully";
        } catch (Exception e) {
            return "Error saving student: " + e.getMessage();
        }
    }

    @Override
    public List<studentDTO> getAllStudents() {
        List<student> students = studentRepo.findAll();
        return students.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public studentDTO updateStudent(studentDTO studentDTO) {
        Optional<student> existingStudent = studentRepo.findById(studentDTO.getId());
        if (existingStudent.isPresent()) {
            student entity = existingStudent.get();
            entity.setTitle(studentDTO.getTitle());
            entity.setName(studentDTO.getName());
            entity.setAddress(studentDTO.getAddress());
            entity.setCity(studentDTO.getCity());
            entity.setCourse(studentDTO.getCourse());
            student updated = studentRepo.save(entity);
            return convertToDTO(updated);
        } else {
            throw new RuntimeException("Student not found with id: " + studentDTO.getId());
        }
    }

    @Override
    public String deleteStudent(Long id) {
        try {
            if (studentRepo.existsById(id)) {
                studentRepo.deleteById(id);
                return "Student deleted successfully";
            } else {
                return "Student not found with id: " + id;
            }
        } catch (Exception e) {
            return "Error deleting student: " + e.getMessage();
        }
    }

    @Override
    public studentDTO getStudentById(Long id) {
        Optional<student> studentOpt = studentRepo.findById(id);
        if (studentOpt.isPresent()) {
            return convertToDTO(studentOpt.get());
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    // Helper method to convert Entity to DTO
    private studentDTO convertToDTO(student entity) {
        studentDTO dto = new studentDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setCourse(entity.getCourse());
        return dto;
    }
}
