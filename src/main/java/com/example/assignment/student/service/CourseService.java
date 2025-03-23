package com.example.assignment.student.service;

import com.example.assignment.student.dto.CourseDTO;

import java.util.List;

public interface CourseService {
     List<CourseDTO> getCourses();
     CourseDTO createCourse(CourseDTO courseDTO);
}
