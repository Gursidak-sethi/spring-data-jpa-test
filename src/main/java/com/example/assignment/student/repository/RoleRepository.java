package com.example.assignment.student.repository;

import com.example.assignment.student.entity.ERole;
import com.example.assignment.student.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
