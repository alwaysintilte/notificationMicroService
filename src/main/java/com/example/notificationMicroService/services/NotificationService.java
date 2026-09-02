package com.example.notificationMicroService.services;

import com.example.notificationMicroService.models.entities.Notification;
import com.example.notificationMicroService.models.enums.Status;
import com.example.notificationMicroService.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }
    public Optional<Notification> findById(Long id){
        return notificationRepository.findById(id);
    }
    public void deleteById(Long id){
        notificationRepository.deleteById(id);
    }
    public List<Notification> findByUserId(String userId){
        return findByUserId(userId);
    }
    public List<Notification> findByStatus(Status status){
        return notificationRepository.findByStatus(status);
    }
}
