package com.example.artisan_finds.user.otp;

import com.example.artisan_finds.user.otp.entity.OTP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends CrudRepository<OTP, String> {
}
