package Library_Management.Springboot.controller;

import Library_Management.Springboot.entity.Notification;
import Library_Management.Springboot.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getRecentNotifications();
    }
}