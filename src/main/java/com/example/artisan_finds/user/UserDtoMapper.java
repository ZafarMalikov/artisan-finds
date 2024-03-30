package com.example.artisan_finds.user;


import com.example.artisan_finds.common.service.GenericDtoMapper;
import com.example.artisan_finds.user.dto.UserCreateDto;
import com.example.artisan_finds.user.dto.UserResponseDto;
import com.example.artisan_finds.user.dto.UserUpdateDto;
import com.example.artisan_finds.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.math.raw.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoMapper extends GenericDtoMapper<User, UserCreateDto, UserUpdateDto, UserResponseDto> {

    private final  ModelMapper modelMapper;
    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        return modelMapper.map(userCreateDto,User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, User user) {
        modelMapper.map(userUpdateDto,user);
    }

    @Override
    public UserCreateDto toCreateDto(User user) {
        return modelMapper.map(user, UserCreateDto.class);
    }
}
