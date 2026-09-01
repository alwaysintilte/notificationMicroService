package com.example.notificationMicroService.repositories;

import com.example.notificationMicroService.models.entities.Notification;
import com.example.notificationMicroService.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByStatus(Status status);
}
