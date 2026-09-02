package com.example.notificationMicroService.models.entities;

import com.example.notificationMicroService.models.enums.NotificationChannel;
import com.example.notificationMicroService.models.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "barber_id")
    private Long barberId;
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_channel", nullable = false)
    private NotificationChannel notificationChannel;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    @Column(name = "notification_text", nullable = false)
    private String notificationText;
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        this.status = Status.CREATED;
        this.createdAt = LocalDateTime.now();
    }
}
