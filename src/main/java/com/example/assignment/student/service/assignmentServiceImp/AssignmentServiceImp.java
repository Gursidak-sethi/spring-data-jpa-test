package com.example.assignment.student.service.assignmentServiceImp;

import com.example.assignment.student.dto.AssignmentDTO;
import com.example.assignment.student.entity.Assignment;
import com.example.assignment.student.entity.Student;
import com.example.assignment.student.repository.AssignmentRepository;
import com.example.assignment.student.repository.StudentRepository;
import com.example.assignment.student.service.AssignmentService;
import com.example.assignment.student.util.AssignmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImp implements AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<AssignmentDTO> getAssignments() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(AssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
        Assignment assignment = AssignmentMapper.toEntity(assignmentDTO); // Convert DTO to Entity

        // If studentId provided, fetch student and set both sides
        if (assignmentDTO.getStudentId() != null) {
            Optional<Student> studentOpt = studentRepository.findById(assignmentDTO.getStudentId());
            if (studentOpt.isEmpty()) {
                throw new RuntimeException("Student with ID " + assignmentDTO.getStudentId() + " not found.");
            }
            Student student = studentOpt.get();
            assignment.setStudent(student);
            student.setAssignment(assignment); // Bidirectional link
        }

        Assignment savedAssignment = assignmentRepository.save(assignment);
        return AssignmentMapper.toDto(savedAssignment); // Convert Entity to DTO
    }
}
