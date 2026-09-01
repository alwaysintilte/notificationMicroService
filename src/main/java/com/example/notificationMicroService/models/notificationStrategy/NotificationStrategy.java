package com.example.notificationMicroService.models.notificationStrategy;

import com.example.notificationMicroService.models.DTOs.NotificationMessageRequest;
import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.models.enums.NotificationChannel;

public interface NotificationStrategy {
    void sendNotification(NotificationMessageRequest messageRequest);
    String createNotificationText(NotificationRequest request);
    NotificationChannel getNotificationChannel();
}
