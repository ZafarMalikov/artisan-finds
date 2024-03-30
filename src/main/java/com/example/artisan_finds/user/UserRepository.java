package com.example.artisan_finds.user;

import com.example.artisan_finds.common.repository.GenericSpecificationRepository;
import com.example.artisan_finds.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends GenericSpecificationRepository<User, Integer> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
