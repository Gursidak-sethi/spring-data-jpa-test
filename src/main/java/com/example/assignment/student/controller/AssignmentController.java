package com.example.assignment.student.controller;

import com.example.assignment.student.dto.AssignmentDTO;
import com.example.assignment.student.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public List<AssignmentDTO> getAssignments(){
        return assignmentService.getAssignments();
    }

    @PostMapping
    public AssignmentDTO createAssignment(@RequestBody AssignmentDTO assignmentDTO){
        return assignmentService.createAssignment(assignmentDTO);
    }
}
