package com.example.artisan_finds.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final List<AbstractNotification> abstractNotifications;

    public AbstractNotification getService(NotificationType notificationType){

        for (AbstractNotification abstractNotification : abstractNotifications) {

            if (abstractNotification.supports(notificationType)) {
                return abstractNotification;
            }
        }
            throw new IllegalArgumentException( "%s notification type is not supported ".formatted( notificationType ) );
    }
}
