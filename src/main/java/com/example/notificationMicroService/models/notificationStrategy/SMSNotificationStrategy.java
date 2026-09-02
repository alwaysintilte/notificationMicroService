package com.example.notificationMicroService.models.notificationStrategy;

import com.example.notificationMicroService.models.DTOs.NotificationMessageRequest;
import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.models.entities.Notification;
import com.example.notificationMicroService.models.enums.NotificationChannel;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component("SMS")
public class SMSNotificationStrategy implements NotificationStrategy{
    private NotificationChannel notificationChannel = NotificationChannel.SMS;

    @Override
    public NotificationChannel getNotificationChannel() {
        return this.notificationChannel;
    }

    @Override
    public String createNotificationText(NotificationRequest request) {
        String date = request.appointmentDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        String message = "Здравствуйте, " + request.clientFirstName() + " " + request.clientLastName() + "! Ваша запись в барбершоп подтверждена. " +
                "Дата и время: " + date + ". " +
                "Барбер: " + request.barberFirstName() + " " + request.barberLastName() + ". " +
                "Адрес: Гродно. " +
                "Если вы не сможете прийти, отмените запись заранее. " +
                "С уважением, Команда барбершопа.";
        return message;
    }

    @Override
    public void sendNotification(NotificationMessageRequest messageRequest) {
        try {
            System.out.println("Sending SMS");
        } catch (Exception e) {
            throw new RuntimeException("Error while sending SMS message" + e.getMessage());
        }
    }
}
