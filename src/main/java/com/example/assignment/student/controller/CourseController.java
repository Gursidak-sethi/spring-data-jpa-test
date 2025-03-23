package com.example.assignment.student.controller;

import com.example.assignment.student.dto.CourseDTO;
import com.example.assignment.student.repository.CourseRepository;
import com.example.assignment.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO){
        return courseService.createCourse(courseDTO);
    }
}
