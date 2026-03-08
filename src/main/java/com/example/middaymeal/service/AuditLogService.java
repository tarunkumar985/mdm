package com.example.middaymeal.service;

import org.springframework.stereotype.Service;

import com.example.middaymeal.entity.AuditLog;
import com.example.middaymeal.entity.User;
import com.example.middaymeal.repository.AuditLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository repo;

    public void log(
            User user,
            String action,
            String resource,
            String resourceRef,
            String ip,
            String status
    ) {
        AuditLog log = new AuditLog();
        log.setUsername(user.getUsername());
        log.setRole(user.getRole());
        log.setAction(action);
        log.setResource(resource);
        log.setResourceRef(resourceRef);
        log.setIpAddress(ip);
        log.setStatus(status);

        repo.save(log);
    }
}