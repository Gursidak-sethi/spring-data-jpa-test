package com.example.assignment.student.controller;

import com.example.assignment.student.dto.request.LoginRequestDTO;
import com.example.assignment.student.dto.request.RegisterRequestDTO;
import com.example.assignment.student.dto.response.MessageResponse;
import com.example.assignment.student.entity.ERole;
import com.example.assignment.student.entity.Role;
import com.example.assignment.student.entity.User;
import com.example.assignment.student.repository.RoleRepository;
import com.example.assignment.student.repository.UserRepository;
import com.example.assignment.student.service.userDetailsServiceImp.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * ✅ REGISTER a new user
     */
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequestDTO registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // Create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);

        // Assign roles
        Set<Role> roles = new HashSet<>();
        if (registerRequest.getRole() == null || registerRequest.getRole().isEmpty()) {
            // Default role if none specified
            Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            roles.add(studentRole);
        } else {
            roles = registerRequest.getRole().stream().map(roleName -> {
                ERole eRole = ERole.valueOf(roleName);
                return roleRepository.findByName(eRole)
                        .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            }).collect(Collectors.toSet());
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    /**
     * ✅ LOGIN user and store authentication in SecurityContext
     */
    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Store authentication in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get authenticated user details
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        System.out.println(userDetails);
        // Create response
        MessageResponse response = new MessageResponse("Login successful");
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ LOGOUT user by clearing SecurityContext
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("User logged out successfully");
    }

    /**
     * ✅ GET CURRENT USER
     */
    @GetMapping("/me")
    public ResponseEntity<MessageResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body(new MessageResponse("User not authenticated"));
        }

        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        System.out.println(userDetails);
        return ResponseEntity.ok(new MessageResponse("User is authenticated"));
    }
}
