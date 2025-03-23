package com.example.assignment.student.service.studentServiceImp;

import com.example.assignment.student.dto.StudentDTO;
import com.example.assignment.student.entity.Assignment;
import com.example.assignment.student.entity.Student;
import com.example.assignment.student.repository.AssignmentRepository;
import com.example.assignment.student.repository.StudentRepository;
import com.example.assignment.student.service.StudentService;
import com.example.assignment.student.util.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<StudentDTO> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDto) {
        Student student = StudentMapper.toEntity(studentDto); // Convert DTO to Entity

        // If assignmentId is provided, fetch assignment and set both sides
        if (studentDto.getAssignmentId() != null) {
            Optional<Assignment> assignmentOpt = assignmentRepository.findById(studentDto.getAssignmentId());
            if (assignmentOpt.isEmpty()) {
                throw new RuntimeException("Assignment with ID " + studentDto.getAssignmentId() + " not found.");
            }
            Assignment assignment = assignmentOpt.get();
            student.setAssignment(assignment);
            assignment.setStudent(student); // Bidirectional link
        }

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.toDto(savedStudent); // Convert Entity to DTO
    }
}
