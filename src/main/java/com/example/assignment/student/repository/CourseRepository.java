package com.example.assignment.student.repository;

import com.example.assignment.student.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
