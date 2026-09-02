package com.example.notificationMicroService.mappers;

import com.example.notificationMicroService.models.DTOs.NotificationMessageRequest;
import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.models.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "barberId", source = "barberId")
    @Mapping(target = "notificationChannel", source = "notificationChannel")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "notificationText", ignore = true)
    @Mapping(target = "appointmentDate", source = "appointmentDate")
    @Mapping(target = "createdAt", ignore = true)
    Notification toEntity(NotificationRequest request);

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "subject", source = "notificationSubject")
    @Mapping(target = "email", source = "clientEmail")
    @Mapping(target = "phone", source = "clientPhone")
    NotificationMessageRequest toMessageRequest(NotificationRequest request);
}
