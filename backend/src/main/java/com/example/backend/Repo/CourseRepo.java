package com.example.backend.Repo;

import com.example.backend.entity.course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<course, Long> {
}

