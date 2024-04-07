package com.example.artisan_finds.user;

import com.example.artisan_finds.common.exception.ExceptionUNAUTHORIZED;
import com.example.artisan_finds.common.exception.PhoneNumberNotVerifiedException;
import com.example.artisan_finds.common.exception.UserNameAllReadyTaken;
import com.example.artisan_finds.common.service.GenericCrudService;
import com.example.artisan_finds.notification.AbstractNotification;
import com.example.artisan_finds.notification.NotificationService;
import com.example.artisan_finds.notification.NotificationType;
import com.example.artisan_finds.user.dto.*;
import com.example.artisan_finds.user.entity.Role;
import com.example.artisan_finds.user.entity.User;
import com.example.artisan_finds.user.otp.OTPRepository;
import com.example.artisan_finds.user.otp.entity.OTP;
import com.example.artisan_finds.user.otp.entity.dto.OtpVerifyDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Getter
public class UserService extends
        GenericCrudService<User, Integer, UserCreateDto, UserUpdateDto, UserPatchDto, UserResponseDto>
        implements UserDetailsService {

    private final UserDtoMapper mapper;
    private final Class<User> entityClass = User.class;
    private final OTPRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;
    private final UserRepository repository;


    @Override
    protected User save(UserCreateDto userCreateDto) {
        User user = mapper.toEntity(userCreateDto);
        return repository.save(user);
    }

    @Override
    protected User updateEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.update(userUpdateDto, user);
        return repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByPhoneNumber(username).orElseThrow(EntityNotFoundException::new);

    }


    @Transactional
    public UserResponseDto register(UserRegisterRequestDto userCreateDto) {
        userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));

        validateUserRegister(userCreateDto);

        OTP otp = modelMapper.map(userCreateDto, OTP.class);
        otp.setSendTime(LocalDateTime.now());
        otp.setSentCount(1);
        otp.setCode(String.valueOf(new Random().nextInt(1000, 9999)));

        AbstractNotification service = notificationService.getService(NotificationType.SMS);
//        boolean isSend = service.sendNotification(new NotificationRequestDto(otp.getPhoneNumber(), "Your verification code: %d ".formatted(otp.getCode())));
        boolean isSend = true;
        if (isSend) {
            OTP save = otpRepository.save(otp);
            return modelMapper.map(save, UserResponseDto.class);
        } else {
            throw new RuntimeException();
        }

    }

    private void validateUserRegister(UserRegisterRequestDto req) {
        Optional<OTP> otp = otpRepository.findById(req.getPhoneNumber());

        if (otp.isPresent()) {
            throw new RuntimeException("sms ol ready");
        } else {
            Optional<User> byPhoneNumber = repository.findUserByPhoneNumber(req.getPhoneNumber());

            if (byPhoneNumber.isPresent()) {
                try {
                    throw new UserNameAllReadyTaken("this username ol ready taken");
                } catch (UserNameAllReadyTaken e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public UserResponseDto verifyCode(OtpVerifyDto otpVerifyDto) {

        OTP otp = otpRepository.findById(otpVerifyDto.getPhoneNumber())
                .orElseThrow(() -> new ExceptionUNAUTHORIZED("You need to register first"));

        if (otp.getSendTime().plusSeconds(60).isBefore(LocalDateTime.now())) {

            if (otp.getSentCount() >= 3) {
                otp.setIsBlocked(true);
                throw new PhoneNumberNotVerifiedException("Please try again in a day");
            } else {

                sentVerificationCode(otp.getPhoneNumber());
            }
            throw new RuntimeException("Your verification code is invalid");
        } else {
            if (otp.getCode().equals(otpVerifyDto.getCode())) {

                User user = modelMapper.map(otp, User.class);
                user.setRole(Role.USER);

                Optional<User> optionalUser = repository.findUserByPhoneNumber(user.getPhoneNumber());
                if (optionalUser.isPresent()) {
                    repository.delete(user);
                } else {
                    repository.save(user);
                }
                otpRepository.delete(otp);

                return mapper.toResponseDto(optionalUser.get());
            }
            throw new PhoneNumberNotVerifiedException("Incorrect verification code");
        }
    }

    public void sentVerificationCode(String phoneNumber) {

        OTP otp = otpRepository.findById(phoneNumber)
                .orElseThrow(() -> new ExceptionUNAUTHORIZED("You need to register first"));


        if (otp.getSentCount() >= 3) {
            throw new PhoneNumberNotVerifiedException("Please try again in a day");
        }

        AbstractNotification service = notificationService.getService(NotificationType.SMS);

        otp.setCode(String.valueOf(new Random().nextInt(1000, 9999)));
        otp.setSentCount(otp.getSentCount() + 1);
        otp.setSendTime(LocalDateTime.now());

//            boolean isSend = service.sendNotification(
//                    NotificationRequestDto.builder()
//                            .message("Your verification code %d".formatted(otp.getCode()))
//                            .phoneNumber(phoneNumber)
//                            .build()
//            );
        boolean isSend = true;
        if (isSend) {
            System.out.println(otp.getCode());
            otpRepository.save(otp);
        } else {
            throw new RuntimeException();
        }

    }


    public UserResponseDto login(UserLoginRequestDto dto) {

        User user = repository.findUserByPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(() -> new EntityNotFoundException("This phone number %s not found ".formatted(dto.getPhoneNumber())));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword()) && !user.getIsBlocked()) {

            AbstractNotification service = notificationService.getService(NotificationType.SMS);

            OTP otp = modelMapper.map(user, OTP.class);
            otp.setCode(String.valueOf(new Random().nextInt(1000, 9999)));
            otp.setSentCount(otp.getSentCount() + 1);
            otp.setSendTime(LocalDateTime.now());

            boolean isSend = true;

            if (isSend) {
                System.out.println(otp.getCode());
                otpRepository.save(otp);
            } else {
                throw new RuntimeException();
            }
        } else {
            throw new RuntimeException(" password is incorrect");
        }

        return mapper.toResponseDto(user);
    }


}
