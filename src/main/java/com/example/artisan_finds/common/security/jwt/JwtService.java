package com.example.artisan_finds.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {

    @Value("${jwt.security.key}")
    private String SECRET_KEY ;

    @Transactional
    public String generateToken(String phoneNumber, Map<String, Object> claims){
        return Jwts.builder()
                .signWith(signKey())
                .addClaims(claims)
                .setSubject(phoneNumber)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.DAYS)))
                .compact();
    }

    @Transactional
    public String generateToken(String phoneNumber){
        return generateToken(phoneNumber, Collections.emptyMap());
    }


    @Transactional
    public Claims claims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Transactional
    protected Key signKey(){
        byte[] decode = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decode);
    }


}
