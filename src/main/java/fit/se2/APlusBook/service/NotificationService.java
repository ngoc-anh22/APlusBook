package fit.se2.APlusBook.service;

import fit.se2.APlusBook.model.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends BaseService<Notification>{
    @Override
    protected Class<Notification> clazz() {
        return Notification.class;
    }
}
