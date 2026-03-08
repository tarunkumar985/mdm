package com.example.middaymeal.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.middaymeal.dto.LoginRequest;
import com.example.middaymeal.entity.RefreshToken;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.security.JwtUtil;
import com.example.middaymeal.service.RefreshTokenService;
import com.example.middaymeal.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;

	private final JwtUtil jwtUtil;

	private final PasswordEncoder passwordEncoder;

	private final RefreshTokenService refreshTokenService;

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//		User user = userService.getByUsername(request.username());
//		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
//			throw new RuntimeException("Invalid credentials");
//		}
//		String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
//		RefreshToken refreshToken = refreshTokenService.create(user);
//		return ResponseEntity.ok(
//				Map.of("accessToken", accessToken, "refreshToken", refreshToken.getToken(), "role", user.getRole()));
//	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		User user = userService.getByUsername(request.username());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
		}
		if (!user.getActive()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "User account disabled"));
		}
		if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
		}
		String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
		RefreshToken refreshToken = refreshTokenService.create(user);
		return ResponseEntity.ok(
				Map.of("accessToken", accessToken, "refreshToken", refreshToken.getToken(), "role", user.getRole()));
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
		User user = refreshTokenService.validateAndGetUser(refreshToken);
		String newAccessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
		return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
	}

}