package com.example.artisan_finds.notification.sms;

import com.example.artisan_finds.notification.AbstractNotification;
import com.example.artisan_finds.notification.NotificationType;
import com.example.artisan_finds.notification.dto.NotificationRequestDto;
import com.example.artisan_finds.notification.sms.dto.EskizRefreshResponseDto;
import com.example.artisan_finds.notification.sms.dto.EskizSmsSentRequestDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SmsNotificationService extends AbstractNotification {

    private final NotificationFeign notificationFeign;

    @Value("${artisan_finds.eskiz.sms.token}")
    private String token;



    @Override
    public boolean sendNotification(NotificationRequestDto requestDttDto) {
        try {
            EskizRefreshResponseDto send = notificationFeign.send(new EskizSmsSentRequestDto(requestDttDto.getPhoneNumber(), requestDttDto.getMessage()), token);
            return true;
        }catch (FeignException.Forbidden | FeignException.Unauthorized ex){
            try {
                EskizRefreshResponseDto refreshToken = notificationFeign.refresh(token);
                token=refreshToken.getData().get("token");
                notificationFeign.send(new EskizSmsSentRequestDto(requestDttDto.getPhoneNumber(), requestDttDto.getMessage()),token);
                return true;
            }catch (Exception e){
                log.error("Exception happend while refreshing eskiz jwt token", e);
                return false;
            }
        }catch (Exception e){
            log.error("Unable to send sms to number .Exception happend", e);
            return false;
        }
    }



    @Override
    public boolean supports(NotificationType notificationType) {
        return notificationType==NotificationType.SMS;
    }
}
