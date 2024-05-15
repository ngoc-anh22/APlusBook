package fit.se2.APlusBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fit.se2.APlusBook.model.Notification;

public interface NotificationRepository extends JpaRepository <Notification, Long> {
    
}
