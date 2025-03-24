package com.example.assignment.student.service.departmentServiceImp;

import com.example.assignment.student.dto.DepartmentDTO;
import com.example.assignment.student.entity.Course;
import com.example.assignment.student.entity.Department;
import com.example.assignment.student.entity.Student;
import com.example.assignment.student.repository.CourseRepository;
import com.example.assignment.student.repository.DepartmentRepository;
import com.example.assignment.student.repository.StudentRepository;
import com.example.assignment.student.service.DepartmentService;
import com.example.assignment.student.util.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<DepartmentDTO> getDepartments(){
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        List<Course> courses;
        if (departmentDTO.getCourseIds() != null && !departmentDTO.getCourseIds().isEmpty()) {
            courses = courseRepository.findAllById(departmentDTO.getCourseIds());
        } else {
            courses = List.of();
        }

        List<Student> students;
        if (departmentDTO.getStudentIds() != null && !departmentDTO.getStudentIds().isEmpty()) {
            students = studentRepository.findAllById(departmentDTO.getStudentIds());
        } else {
            students = List.of();
        }

        Department department = DepartmentMapper.toEntity(departmentDTO, courses, students);

        for (Student student : students) {
            student.setDepartment(department);
        }



        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDTO(savedDepartment);
    }


}
