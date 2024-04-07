package com.example.artisan_finds;

import jakarta.validation.constraints.Max;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Arrays;
import java.util.regex.Pattern;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
@EnableRedisRepositories
public class ArtisanFindsApplication {
    //todo OAuth2,product ni ichma ich categorisini qilish,card,
    public static void main(String[] args) {

        SpringApplication.run(ArtisanFindsApplication.class, args);
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (String s : word1) {
            stringBuilder1.append(s);
        }
        for (String s : word2) {
            stringBuilder2.append(s);
        }
        return stringBuilder1.compareTo(stringBuilder2) == 0;
    }
}


