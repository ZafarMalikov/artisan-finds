package com.example.artisan_finds;

import com.example.artisan_finds.user.UserRepository;
import com.example.artisan_finds.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {


        private final UserRepository repository;
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER','USER')")
    public ResponseEntity<User> post(){
        User user = repository.findById(1).get();
        System.out.println("helloodas");
        return ResponseEntity.ok(user);
    }
}
