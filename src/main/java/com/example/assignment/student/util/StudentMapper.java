package com.example.assignment.student.util;

import com.example.assignment.student.dto.StudentDTO;
import com.example.assignment.student.entity.Assignment;
import com.example.assignment.student.entity.Student;

public class StudentMapper {

    public static StudentDTO toDto(Student student) {
        StudentDTO studentDto = new StudentDTO();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setStudentName(student.getStudentName());

        if (student.getAssignment() != null) {
            studentDto.setAssignmentId(student.getAssignment().getAssignmentId());
        }
        return studentDto;
    }

    public static Student toEntity(StudentDTO studentDto) {
        Student student = new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getStudentName());

        if (studentDto.getAssignmentId() != null) {
            Assignment assignment = new Assignment();
            assignment.setAssignmentId(studentDto.getAssignmentId());
            student.setAssignment(assignment);
        }
        return student;
    }
}
