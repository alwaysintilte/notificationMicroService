package com.example.notificationMicroService.models.DTOs;

public record ServiceInfo(
        String name,
        Integer duration,
        Double price
) {}
