package com.example.artisan_finds.user.otp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(timeToLive = 3600)
public class OTP {

    @Id
    private String phoneNumber;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String code;
    private LocalDateTime sendTime;
    private int sentCount;
    private Boolean isBlocked=false;

}

