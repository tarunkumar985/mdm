package com.example.middaymeal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.middaymeal.entity.AuditLog;
@Repository
public interface AuditLogRepository
extends JpaRepository<AuditLog, Long> {

List<AuditLog> findByUsernameOrderByCreatedAtDesc(String username);
}