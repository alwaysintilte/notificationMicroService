package com.example.notificationMicroService.models.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class NotificationMessageRequest {
    private String message;
    private String subject;
    private String email;
    private String phone;
}