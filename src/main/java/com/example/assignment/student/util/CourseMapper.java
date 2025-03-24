package com.example.assignment.student.util;

import com.example.assignment.student.dto.CourseDTO;
import com.example.assignment.student.entity.Course;
import com.example.assignment.student.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDTO(Course course){
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        if(course.getDepartments()!=null){
            List<Long> departments = course.getDepartments().stream().map(Department::getDepartmentId).collect(Collectors.toList());
            courseDTO.setDepartmentIds(departments);
        }
        return courseDTO;
    }

    public static Course toEntity(CourseDTO courseDTO,List<Department> departments){
        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId());
        course.setCourseName(courseDTO.getCourseName());
        course.setDepartments(departments);
        for (Department department : departments) {
            department.getCourses().add(course);
        }
        return course;
    }
}
