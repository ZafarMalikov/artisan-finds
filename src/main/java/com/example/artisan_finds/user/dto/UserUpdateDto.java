package com.example.artisan_finds.user.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
}
