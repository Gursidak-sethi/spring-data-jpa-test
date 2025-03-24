package com.example.assignment.student.service.studentServiceImp;

import com.example.assignment.student.dto.StudentDTO;
import com.example.assignment.student.entity.Assignment;
import com.example.assignment.student.entity.Department;
import com.example.assignment.student.entity.Student;
import com.example.assignment.student.repository.AssignmentRepository;
import com.example.assignment.student.repository.DepartmentRepository;
import com.example.assignment.student.repository.StudentRepository;
import com.example.assignment.student.service.StudentService;
import com.example.assignment.student.util.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public StudentDTO createStudent(StudentDTO studentDto) {
        Student student = StudentMapper.toEntity(studentDto);

        if (studentDto.getAssignmentId() != null) {
            Optional<Assignment> assignmentOpt = assignmentRepository.findById(studentDto.getAssignmentId());
            if (assignmentOpt.isEmpty()) {
                throw new RuntimeException("Assignment with ID " + studentDto.getAssignmentId() + " not found.");
            }
            Assignment assignment = assignmentOpt.get();
            student.setAssignment(assignment);
            assignment.setStudent(student);
        }

        if(studentDto.getDepartmentId()!=null){
            Optional<Department> departmentOptional = departmentRepository.findById(studentDto.getDepartmentId());
            if(departmentOptional.isEmpty()){
                throw new RuntimeException("Department with ID "+ studentDto.getDepartmentId()+" not found.");
            }
            Department department = departmentOptional.get();
            student.setDepartment(department);
        }

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDto(savedStudent);
    }
}
