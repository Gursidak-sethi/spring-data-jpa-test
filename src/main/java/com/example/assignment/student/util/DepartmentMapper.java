package com.example.assignment.student.util;

import com.example.assignment.student.dto.DepartmentDTO;
import com.example.assignment.student.entity.Course;
import com.example.assignment.student.entity.Department;
import com.example.assignment.student.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentId(department.getDepartmentId());
        departmentDTO.setDepartmentName(department.getDepartmentName());

        if (department.getStudents() != null) {
            List<Long> students = department.getStudents().stream().map(Student::getStudentId).collect(Collectors.toList());
            departmentDTO.setStudentIds(students);
        }
        if (department.getCourses() != null) {
            List<Long> courses = department.getCourses().stream().map(Course::getCourseId).collect(Collectors.toList());
            departmentDTO.setCourseIds(courses);
        }
        return departmentDTO;
    }

    public static Department toEntity(DepartmentDTO departmentDTO, List<Course> courses, List<Student> students) {
        Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setCourses(courses);
        department.setStudents(students);
        return department;
    }
}
