package com.demo.Xparties.Tinder.Controller;

import com.demo.Xparties.Tinder.Security.JWT.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @GetMapping("/redirect")
    public void redirectToFrontend(
            @RequestParam String userId,
            @RequestParam String email,
            @RequestParam String name,
            HttpServletResponse response
    ) throws IOException {
        // Generate a JWT token (you can pass the username or other details as needed)
        String token = JwtUtil.generateToken(userId, email, name);

        // Set the JWT token in a cookie
        ResponseCookie cookie = ResponseCookie.from("JWT_TOKEN_XPARTIESTINDER", token)
                .httpOnly(true)
                .secure(true)
                .domain("xpartiestinder.com")
                .path("/")
                .maxAge(86400)
                .sameSite("None")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        // Redirect to the frontend
        response.sendRedirect("https://www.xpartiestinder.com/events");
    }
}
