package com.example.notificationMicroService.models.notificationStrategy;

import com.example.notificationMicroService.models.DTOs.NotificationMessageRequest;
import com.example.notificationMicroService.models.DTOs.NotificationRequest;
import com.example.notificationMicroService.models.entities.Notification;
import com.example.notificationMicroService.models.enums.NotificationChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component("EMAIL")
@RequiredArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy{
    private NotificationChannel notificationChannel = NotificationChannel.EMAIL;
    private final JavaMailSender javaMailSender;
    @Override
    public NotificationChannel getNotificationChannel() {
        return this.notificationChannel;
    }

    @Override
    public String createNotificationText(NotificationRequest request) {
        String date = request.appointmentDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        String services = request.services().stream().map(service -> "" + service.name() + " (" + service.duration() + " мин) — " + service.price() + " BYN").collect(Collectors.joining("\n"));
        double total = request.services().stream().mapToDouble(service -> service.price()).sum();
        String message = "Здравствуйте, " + request.clientFirstName() + " " + request.clientLastName() + "!\n\n" +
                "Ваша запись в барбершоп подтверждена.\n\n" +
                "Дата и время: " + date + "\n" +
                "Услуги:\n" + services + "\n" +
                "Итого: " + String.format("%.0f", total) + " BYN\n\n" +
                "Барбер: " + request.barberFirstName() + " " + request.barberLastName() + "\n" +
                "Адрес: Гродно\n\n" +
                "Если вы не сможете прийти, отмените запись заранее.\n\n" +
                "С уважением,\nКоманда барбершопа.";
        return message;
    }

    @Override
    public void sendNotification(NotificationMessageRequest messageRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(messageRequest.getEmail());
            message.setSubject(messageRequest.getSubject());
            message.setText(messageRequest.getMessage());
            javaMailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException("Error while sending email message: " + e.getMessage());
        }
    }
}
