package com.demo.Xparties.Tinder.Security.JWT;

import com.demo.Xparties.Tinder.Exception.JwtException.JwtAuthenticationError;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtTokenExpired;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtTokenInvalid;
import com.demo.Xparties.Tinder.Exception.JwtException.JwtTokenNotValidated;
import com.demo.Xparties.Tinder.Service.User.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String token = JwtUtil.extractTokenFromCookie(request);

            if (token != null) {
                validateAndAuthenticateUser(token, request, response);
            }

        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpired("JWT Token is expired.");
        } catch (MalformedJwtException | SignatureException e) {
            throw new JwtTokenInvalid("Invalid JWT Token.");
        } catch (Exception e) {
            throw new JwtAuthenticationError("Error in JWT authentication.");
        }

        filterChain.doFilter(request, response);
    }

    private void validateAndAuthenticateUser(String token, HttpServletRequest request, HttpServletResponse response) {
        if (!JwtUtil.validateToken(token)) {
            JwtUtil.removeInvalidJwtTokenFromCookie(response);
            throw new JwtTokenNotValidated("JWT token could not be validated.");
        } else {
            UserDetails userDetails = null;

            try {

                String email = JwtUtil.getEmailFromToken(token);
                userDetails = userService.loadUserByEmail(email);

            } catch (Exception e) {
                JwtUtil.removeInvalidJwtTokenFromCookie(response);
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                assert userDetails != null;
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }
}