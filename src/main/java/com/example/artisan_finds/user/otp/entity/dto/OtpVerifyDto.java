package com.example.artisan_finds.user.otp.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerifyDto {

    private String phoneNumber;
    private String code;
}
