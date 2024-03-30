package com.example.artisan_finds.notification;


import com.example.artisan_finds.notification.dto.NotificationRequestDto;

public abstract class AbstractNotification {

    public abstract boolean sendNotification(NotificationRequestDto requestDttDto);

    public  abstract boolean supports(NotificationType notificationType);
}
