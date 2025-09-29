package com.example.backend.Service.IMPL;

import com.example.backend.DTO.studentDTO;
import com.example.backend.Service.studentService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class studentServiceIMPL implements studentService {

    private final DatabaseReference studentRef;

    @Autowired
    public studentServiceIMPL(FirebaseApp firebaseApp) {
        this.studentRef = FirebaseDatabase.getInstance(firebaseApp).getReference("students");
    }

    @Override
    public String saveStudent(studentDTO studentDTO) {
        try {
            String key = studentRef.push().getKey();
            studentDTO.setId(key); // Use String for id
            studentRef.child(key).setValueAsync(studentDTO);
            return "Student saved successfully";
        } catch (Exception e) {
            return "Error saving student: " + e.getMessage();
        }
    }

    @Override
    public List<studentDTO> getAllStudents() {
        List<studentDTO> studentList = new ArrayList<>();
        try {
            // Use Firebase Database API for async data retrieval
            final Object lock = new Object();
            final List<studentDTO> tempList = new ArrayList<>();
            studentRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (com.google.firebase.database.DataSnapshot child : snapshot.getChildren()) {
                            studentDTO dto = child.getValue(studentDTO.class);
                            if (dto != null) tempList.add(dto);
                        }
                    }
                    synchronized (lock) {
                        lock.notify();
                    }
                }
                @Override
                public void onCancelled(com.google.firebase.database.DatabaseError error) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            });
            synchronized (lock) {
                lock.wait(3000); // Wait for up to 3 seconds for async callback
            }
            studentList.addAll(tempList);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return studentList;
    }

    @Override
    public studentDTO updateStudent(studentDTO studentDTO) {
        try {
            studentRef.child(studentDTO.getId()).setValueAsync(studentDTO);
            return studentDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String deleteStudent(Long id) {
        return "";
    }

    @Override
    public studentDTO getStudentById(Long id) {
        return null;
    }

    @Override
    public String deleteStudent(String id) {
        try {
            studentRef.child(id).removeValueAsync();
            return "Student deleted successfully";
        } catch (Exception e) {
            return "Error deleting student: " + e.getMessage();
        }
    }

    @Override
    public studentDTO getStudentById(String id) {
        final Object lock = new Object();
        final studentDTO[] result = new studentDTO[1];
        studentRef.child(id).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    result[0] = snapshot.getValue(studentDTO.class);
                }
                synchronized (lock) {
                    lock.notify();
                }
            }
            @Override
            public void onCancelled(com.google.firebase.database.DatabaseError error) {
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        try {
            synchronized (lock) {
                lock.wait(3000); // Wait for up to 3 seconds for async callback
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return result[0];
    }
}
