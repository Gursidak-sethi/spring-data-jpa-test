package com.example.assignment.student.controller;

import com.example.assignment.student.dto.StudentDTO;
import com.example.assignment.student.service.StudentService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RolesAllowed({"ROLE_STUDENT","ROLE_TEACHER","ROLE_HEAD"})
    @GetMapping
    public List<StudentDTO> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO student){
        return studentService.createStudent(student);
    }
}
