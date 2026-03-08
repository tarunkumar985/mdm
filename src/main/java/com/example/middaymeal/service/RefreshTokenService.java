package com.example.middaymeal.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.middaymeal.entity.RefreshToken;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    @Transactional
    public RefreshToken create(User user) {

        repo.deleteByUser(user); // one token per user

        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
        rt.setToken(UUID.randomUUID().toString());
        rt.setExpiryDate(LocalDateTime.now().plusDays(14));

        return repo.save(rt);
    }

    public User validateAndGetUser(String token) {

        RefreshToken rt = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }
        return rt.getUser();
    }
}