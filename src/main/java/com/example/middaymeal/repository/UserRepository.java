package com.example.middaymeal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByRole(String role);

    List<User> findBySchool_Id(Long schoolId);
}