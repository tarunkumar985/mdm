//package com.example.middaymeal.util;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//public class PasswordCheck {
//    public static void main(String[] args) {
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String rawPassword = "Admin@123";
//
//        String hash = "$2a$10$tg1yo06s9rZnDqVGA1qQ7./hXgHc7PumnJJIsW5.Qb2S017.rNRRq";
//
//        boolean matches = encoder.matches(rawPassword, hash);
//
//        System.out.println(matches);
//        
//        System.out.println(encoder.encode(rawPassword));
//        
//    }
//}