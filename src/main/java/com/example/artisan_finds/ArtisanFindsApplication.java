package com.example.artisan_finds;

import com.example.artisan_finds.common.service.GenericCrudService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
@EnableRedisRepositories
public class ArtisanFindsApplication {
    //todo OAuth2,product ni ichma ich categorisini qilish,card,
    public static void main(String[] args) {

        SpringApplication.run(ArtisanFindsApplication.class, args);
    }

}


