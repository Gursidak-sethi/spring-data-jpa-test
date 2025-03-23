package com.example.assignment.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long departmentId;
    private String departmentName;
    private List<Long> studentIds;
    private List<Long> courseIds;
}
