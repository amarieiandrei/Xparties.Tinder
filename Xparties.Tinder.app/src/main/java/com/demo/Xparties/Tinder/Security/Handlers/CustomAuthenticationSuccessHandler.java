package com.demo.Xparties.Tinder.Security.Handlers;

import com.demo.Xparties.Tinder.Exception.OAuth2Exception.OAuth2ProviderNotSupported;
import com.demo.Xparties.Tinder.Security.JWT.JwtUtil;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String userId;
        String email;
        String name;

        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            Map<String, Object> attributes = oidcUser.getAttributes();
            userId = attributes.get("sub").toString();
            email = attributes.get("email").toString();
            name = attributes.get("name").toString();
        } else if (authentication.getPrincipal() instanceof OAuth2User oAuth2User) {
            Map<String, Object> attributes = oAuth2User.getAttributes();
            userId = attributes.get("id").toString();
            email = attributes.get("email").toString();
            name = attributes.get("name").toString();
        } else {
            throw new OAuth2ProviderNotSupported("Authentication type not supported");
        }

        String token = JwtUtil.generateToken(userId, email, name);

//        JwtUtil.setJwtTokenToCookie(token, response);

        long expirationTime = Long.parseLong(System.getenv(JwtUtil.EXPIRATION_TIME));

        // METHOD 2
        // ✅ Use ResponseCookie (More Reliable than Cookie)
        ResponseCookie jwtCookie = ResponseCookie.from("JWT_TOKEN_XPARTIESTINDER", token)
                .httpOnly(true)
                .secure(true)  // Only send over HTTPS
                .sameSite("None")  // Cross-origin cookie allowed
                .domain("xpartiestinder.com")  // Available across subdomains
                .path("/")  // Available for all paths
                .maxAge(expirationTime / 1000)  // Convert ms to seconds
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // ✅ Use 303 See Other Instead of 302
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", "https://www.xpartiestinder.com/events");

        // METHOD 1
//        Cookie cookie = new Cookie("JWT_TOKEN_XPARTIESTINDER", token);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true); // Works only over HTTPS
//        cookie.setPath("/");
//        cookie.setDomain("xpartiestinder.com");
//        cookie.setMaxAge((int) (expirationTime / 1000));
//        cookie.setAttribute("SameSite", "Strict");
//        cookie.setAttribute("SameSite", "None");
//          cookie.setAttribute("SameSite", "Lax");

//        response.addCookie(cookie);

//        response.sendRedirect("https://www.xpartiestinder.com");
//        response.sendRedirect("/api/event/events");
//        response.sendRedirect("http://localhost:4200/events");
    }
}