package com.demo.Xparties.Tinder.Service.Authentication;

import com.demo.Xparties.Tinder.Converter.UserConverter;
import com.demo.Xparties.Tinder.Dto.UserDto.UserResponseDto;
import com.demo.Xparties.Tinder.Exception.AuthenticationException.CurrentUserNotFound;
import com.demo.Xparties.Tinder.Exception.AuthenticationException.UnauthenticatedUser;
import com.demo.Xparties.Tinder.Model.Entity.User;
import com.demo.Xparties.Tinder.Repository.UserRepository;
import com.demo.Xparties.Tinder.Security.JWT.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public Boolean checkAuthentication(Authentication authentication) {
        try {

            return authentication != null && authentication.isAuthenticated() && (
                    authentication.getPrincipal() instanceof OAuth2User || authentication.getPrincipal() instanceof OidcUser
            );

        } catch (Exception e) {
            throw new UnauthenticatedUser("User is not authenticated. Please log in.");
        }
    }

    @Override
    public UserResponseDto getCurrentUser(HttpServletRequest request) {
        try {

            String token = JwtUtil.extractTokenFromCookie(request);

            String email = JwtUtil.getEmailFromToken(token);
            String providerId = JwtUtil.getProviderIdFromToken(token);

            return userConverter.fromEntityToResponseDto(
                    userRepository.findByEmailAndProviderId(email, providerId).get()
            );

        } catch (Exception e) {
            throw new CurrentUserNotFound("Current user could not be found.");
        }
    }
}