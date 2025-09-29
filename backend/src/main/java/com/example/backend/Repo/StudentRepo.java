package com.example.backend.Repo;

import com.example.backend.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<student, Long> {
}

