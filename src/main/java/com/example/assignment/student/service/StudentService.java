package com.example.assignment.student.service;

import com.example.assignment.student.dto.StudentDTO;

import java.util.List;

public interface StudentService {
     List<StudentDTO> getStudents();

     StudentDTO createStudent(StudentDTO student);

}
