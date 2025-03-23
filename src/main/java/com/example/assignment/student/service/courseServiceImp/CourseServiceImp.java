package com.example.assignment.student.service.courseServiceImp;

import com.example.assignment.student.dto.CourseDTO;
import com.example.assignment.student.entity.Course;
import com.example.assignment.student.entity.Department;
import com.example.assignment.student.repository.CourseRepository;
import com.example.assignment.student.repository.DepartmentRepository;
import com.example.assignment.student.service.CourseService;
import com.example.assignment.student.util.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<CourseDTO> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(CourseMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse(CourseDTO courseDTO) {
        List<Department> departments = departmentRepository.findAllById(courseDTO.getDepartmentIds());
        Course course = CourseMapper.toEntity(courseDTO,departments);
        Course saved = courseRepository.save(course);
        return CourseMapper.toDTO(saved);
    }
}
