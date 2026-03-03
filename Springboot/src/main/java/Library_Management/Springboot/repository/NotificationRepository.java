package Library_Management.Springboot.repository;

import Library_Management.Springboot.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Get latest 20 notifications
    List<Notification> findTop20ByOrderByTimestampDesc();
}