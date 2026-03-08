package com.example.middaymeal.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.middaymeal.dto.CreateUserRequest;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SchoolService schoolService;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        // 🔐 Role-based rules
        if ("SCHOOL".equals(request.role())) {
            if (request.schoolId() == null) {
                throw new IllegalArgumentException("School ID required for SCHOOL role");
            }
            user.setSchool(schoolService.getById(request.schoolId()));
        } else {
            user.setSchool(null);
        }

        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}