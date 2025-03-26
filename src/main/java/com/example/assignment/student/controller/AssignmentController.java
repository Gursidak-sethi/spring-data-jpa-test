package com.example.assignment.student.controller;

import com.example.assignment.student.dto.AssignmentDTO;
import com.example.assignment.student.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public List<AssignmentDTO> getAssignments(){
        return assignmentService.getAssignments();
    }
    @GetMapping("{id}")
    public AssignmentDTO getAssignmentById(@PathVariable Long id){
        return assignmentService.getAssignmentById(id);
    }
    @PatchMapping("{id}")
    public AssignmentDTO updateAssignment(@PathVariable Long id, @RequestBody AssignmentDTO assignmentDTO){
        return assignmentService.updateAssignment(id,assignmentDTO);
    }
    @PostMapping
    public AssignmentDTO createAssignment(@RequestBody AssignmentDTO assignmentDTO){
        return assignmentService.createAssignment(assignmentDTO);
    }
}
