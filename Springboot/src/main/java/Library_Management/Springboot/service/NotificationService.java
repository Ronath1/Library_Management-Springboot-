package Library_Management.Springboot.service;

import Library_Management.Springboot.entity.Notification;
import Library_Management.Springboot.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void createNotification(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public List<Notification> getRecentNotifications() {
        return notificationRepository.findTop20ByOrderByTimestampDesc();
    }
}