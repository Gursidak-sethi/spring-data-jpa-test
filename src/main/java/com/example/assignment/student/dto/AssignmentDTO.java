package com.example.assignment.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Long assignmentId;
    private String assignmentName;
    private Long studentId;
}
