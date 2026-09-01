package com.example.notificationMicroService.models.DTOs;

import java.time.LocalDateTime;
import java.util.List;

public record NotificationRequest (
        LocalDateTime appointmentDate,
        Long clientId,
        String clientFirstName,
        String clientLastName,
        String clientPhone,
        String clientEmail,
        Long barberId,
        String barberFirstName,
        String barberLastName,
        String notificationChannel,
        String notificationSubject,
        List<ServiceInfo> services
) {}
