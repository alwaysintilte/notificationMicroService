package com.example.notificationMicroService.services.kafka;

import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.services.NotificationStrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationStrategyService notificationStrategyService;
    @KafkaListener(topics = "booking-notifications", groupId = "notification-service-group")
    public void consumeNotificationRequest(NotificationRequest request){
        if(request == null){
            throw new RuntimeException("Notification Request is null");
        }
        try{
            notificationStrategyService.sendNotification(request);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
