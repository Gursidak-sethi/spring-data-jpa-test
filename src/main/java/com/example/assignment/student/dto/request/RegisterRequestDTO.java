package com.example.assignment.student.dto.request;

import lombok.Data;

import java.util.Set;
@Data
public class RegisterRequestDTO {
    private String username;

    private String email;

    private Set<String> role;

    private String password;
}
