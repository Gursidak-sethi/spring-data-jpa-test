package com.example.assignment.student.service;

import com.example.assignment.student.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {
     List<AssignmentDTO> getAssignments();
     AssignmentDTO getAssignmentById(Long id);
     AssignmentDTO createAssignment(AssignmentDTO assignmentDTO);
     AssignmentDTO updateAssignment(Long id, AssignmentDTO assignmentDTO);
}
