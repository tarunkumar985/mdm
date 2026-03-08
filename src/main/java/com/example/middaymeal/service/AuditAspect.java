package com.example.middaymeal.service;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.middaymeal.entity.User;
import com.example.middaymeal.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogService auditLogService;
    private final SecurityUtil securityUtil;
    private final HttpServletRequest request;

    @Around("@annotation(auditable)")
    public Object audit(
            ProceedingJoinPoint pjp,
            Auditable auditable
    ) throws Throwable {

        User user = securityUtil.getCurrentUser();
        String ip = request.getRemoteAddr();

        try {
            Object result = pjp.proceed();

            auditLogService.log(
                    user,
                    auditable.action(),
                    auditable.resource(),
                    pjp.getArgs().length > 0
                            ? Arrays.toString(pjp.getArgs())
                            : "",
                    ip,
                    "SUCCESS"
            );
            return result;

        } catch (Exception ex) {
            auditLogService.log(
                    user,
                    auditable.action(),
                    auditable.resource(),
                    "ERROR",
                    ip,
                    "DENIED"
            );
            throw ex;
        }
    }
}