package com.example.artisan_finds.user;

import com.example.artisan_finds.common.security.jwt.JwtService;
import com.example.artisan_finds.user.dto.UserLoginRequestDto;
import com.example.artisan_finds.user.dto.UserRegisterRequestDto;
import com.example.artisan_finds.user.dto.UserResponseDto;
import com.example.artisan_finds.user.dto.UserUpdateDto;
import com.example.artisan_finds.user.otp.entity.dto.OtpVerifyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterDto){
        UserResponseDto userResponseDto=userService.register(userRegisterDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }


    @PostMapping("/register/verify/phone-number")
    public ResponseEntity<UserResponseDto>smsVerify(@RequestBody OtpVerifyDto otpVerifyDto){

        UserResponseDto userResponseDto=userService.verifyCode(otpVerifyDto);
        String token = jwtService.generateToken(otpVerifyDto.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+token)
                .body(userResponseDto);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto>login(@RequestBody UserLoginRequestDto dto){
        UserResponseDto loginDto = userService.login(dto);
        System.out.println(dto.getPassword()+" "+dto.getPhoneNumber());
        return ResponseEntity.ok(loginDto);
    }

    @PostMapping("/login/phone-number/verification")
    public ResponseEntity<UserResponseDto>loginSmsVerification(@RequestBody OtpVerifyDto otpVerifyDto){
        UserResponseDto userResponseDto = userService.verifyCode(otpVerifyDto);
        System.out.println(otpVerifyDto.getPhoneNumber()+" "+otpVerifyDto.getCode());
        String token = jwtService.generateToken(otpVerifyDto.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION,"Bearer "+token)
                .body(userResponseDto);
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto>getUser(@PathVariable("id")Integer id){
        UserResponseDto user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>>getAll(Pageable pageable,String predication){
        Page<UserResponseDto> all = userService.getAll(pageable, predication);
        return ResponseEntity.ok(all);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto>update(@PathVariable("id")Integer id,@RequestBody UserUpdateDto userUpdateDto){
        UserResponseDto update = userService.update(id, userUpdateDto);

        return ResponseEntity.ok(update);
    }
}
