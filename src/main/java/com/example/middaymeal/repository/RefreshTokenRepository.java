package com.example.middaymeal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.RefreshToken;
import com.example.middaymeal.entity.User;
@Repository
public interface RefreshTokenRepository
extends JpaRepository<RefreshToken, Long> {

Optional<RefreshToken> findByToken(String token);
@Modifying
@Query("DELETE FROM RefreshToken rt WHERE rt.user = :user")
void deleteByUser(User user);
}