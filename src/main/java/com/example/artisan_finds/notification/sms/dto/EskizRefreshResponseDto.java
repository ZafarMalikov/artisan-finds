package com.example.artisan_finds.notification.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EskizRefreshResponseDto {

    private HashMap<String , String >data;
}
