package com.example.assignment.student.util;

import com.example.assignment.student.dto.AssignmentDTO;
import com.example.assignment.student.entity.Assignment;
import com.example.assignment.student.entity.Student;

public class AssignmentMapper {

    public static AssignmentDTO toDto(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setAssignmentId(assignment.getAssignmentId());
        assignmentDTO.setAssignmentName(assignment.getAssignmentName());

        if (assignment.getStudent() != null) {
            assignmentDTO.setStudentId(assignment.getStudent().getStudentId());
        }
        return assignmentDTO;
    }

    public static Assignment toEntity(AssignmentDTO assignmentDTO) {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(assignmentDTO.getAssignmentId());
        assignment.setAssignmentName(assignmentDTO.getAssignmentName());

        if (assignmentDTO.getStudentId() != null) {
            Student student = new Student();
            student.setStudentId(assignmentDTO.getStudentId());
            assignment.setStudent(student);
        }

        return assignment;
    }
}

