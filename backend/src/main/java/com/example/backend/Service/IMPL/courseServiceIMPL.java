package com.example.backend.Service.IMPL;

import com.example.backend.DTO.courseDTO;
import com.example.backend.Service.CourseService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class courseServiceIMPL implements CourseService {

    private final DatabaseReference courseRef;

    @Autowired
    public courseServiceIMPL(FirebaseApp firebaseApp) {
        this.courseRef = FirebaseDatabase.getInstance(firebaseApp).getReference("courses");
    }

    @Override
    public String saveCourse(courseDTO courseDTO) {
        try {
            String key = courseRef.push().getKey();
            courseDTO.setId(key); // Use String for id
            courseRef.child(key).setValueAsync(courseDTO);
            return "Course saved successfully";
        } catch (Exception e) {
            return "Error saving course: " + e.getMessage();
        }
    }

    @Override
    public List<courseDTO> getAllCourses() {
        List<courseDTO> courseList = new ArrayList<>();
        try {
            // Use Firebase Database API for async data retrieval
            final Object lock = new Object();
            final List<courseDTO> tempList = new ArrayList<>();
            courseRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (com.google.firebase.database.DataSnapshot child : snapshot.getChildren()) {
                            courseDTO dto = child.getValue(courseDTO.class);
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
            courseList.addAll(tempList);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return courseList;
    }

    @Override
    public courseDTO updateCourse(courseDTO courseDTO) {
        try {
            courseRef.child(courseDTO.getId()).setValueAsync(courseDTO);
            return courseDTO;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String deleteCourse(String id) {
        try {
            courseRef.child(id).removeValueAsync();
            return "Course deleted successfully";
        } catch (Exception e) {
            return "Error deleting course: " + e.getMessage();
        }
    }

    @Override
    public courseDTO getCourseById(String id) {
        final Object lock = new Object();
        final courseDTO[] result = new courseDTO[1];
        courseRef.child(id).addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    result[0] = snapshot.getValue(courseDTO.class);
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
