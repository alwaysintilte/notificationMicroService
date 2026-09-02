package com.example.notificationMicroService.models.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessageRequest {
    private String message;
    private String subject;
    private String email;
    private String phone;
}