package com.example.notificationMicroService.services;

import com.example.notificationMicroService.mappers.NotificationMapper;
import com.example.notificationMicroService.models.DTOs.NotificationMessageRequest;
import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.models.entities.Notification;
import com.example.notificationMicroService.models.enums.NotificationChannel;
import com.example.notificationMicroService.models.enums.Status;
import com.example.notificationMicroService.models.notificationStrategy.NotificationStrategy;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationStrategyService {
    private final NotificationMapper notificationMapper;
    private final NotificationService notificationService;
    private final List<NotificationStrategy> notificationStrategyList;
    private Map<NotificationChannel, NotificationStrategy> notificationStrategies;
    @PostConstruct
    private void initStrategies(){
        notificationStrategies = notificationStrategyList.stream()
                .collect(Collectors.toMap(
                        notificationStrategy -> notificationStrategy.getNotificationChannel(),
                        notificationStrategy -> notificationStrategy)
                );
    }
    @Transactional
    public void sendNotification(NotificationRequest request){
        NotificationChannel notificationChannel;
        try {
            notificationChannel = NotificationChannel.valueOf(request.notificationChannel());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert NotificationChannel to enum");
        }
        NotificationStrategy notificationStrategy = notificationStrategies.get(notificationChannel);

        Notification notification = notificationMapper.toEntity(request);
        String notificationMessage = createNotificationText(request);
        notification.setNotificationText(notificationMessage);
        notificationService.save(notification);

        try {
            NotificationMessageRequest notificationMessageRequest = notificationMapper.toMessageRequest(request);
            notificationMessageRequest.setMessage(notificationMessage);
            notificationStrategy.sendNotification(notificationMessageRequest);
            notification.setStatus(Status.SENT);
        } catch (Exception e) {
            notification.setStatus(Status.ERROR);
            throw new RuntimeException("Error while sending message: " + e.getMessage());
        } finally {
            notificationService.save(notification);
        }
    }
    public String createNotificationText(NotificationRequest request){
        NotificationChannel notificationChannel;
        try {
            notificationChannel = NotificationChannel.valueOf(request.notificationChannel());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert NotificationChannel to enum");
        }
        NotificationStrategy notificationStrategy = notificationStrategies.get(notificationChannel);

        return notificationStrategy.createNotificationText(request);
    }
}
