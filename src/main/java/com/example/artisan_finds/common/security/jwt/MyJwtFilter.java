package com.example.artisan_finds.common.security.jwt;

 import com.example.artisan_finds.user.entity.User;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyJwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (StringUtils.isBlank(header) || !header.startsWith("Bearer ")){
                filterChain.doFilter(request,response);
                return;
            }
            String token = header.substring(7);
            Claims claims = jwtService.claims(token);
            String phone = claims.getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(phone);

            User user = (User) userDetails;

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user, null,userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request,response);

        } catch (Exception e){
            log.error(e.getMessage(),e);
            filterChain.doFilter(request,response);
        }
    }
}
