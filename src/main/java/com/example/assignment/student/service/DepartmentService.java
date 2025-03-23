package com.example.assignment.student.service;

import com.example.assignment.student.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
     List<DepartmentDTO> getDepartments();
     DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
}
