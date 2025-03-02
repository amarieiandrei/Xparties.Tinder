package com.demo.Xparties.Tinder.Controller;

import com.demo.Xparties.Tinder.Dto.UserDto.UserResponseDto;
import com.demo.Xparties.Tinder.Service.Authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/authentication")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping("/check")
    public Boolean checkAuthentication(Authentication authentication) {
        return authenticationService.checkAuthentication(authentication);
    }

    @GetMapping("/getCurrentUser")
    public UserResponseDto getCurrentUser(HttpServletRequest request) {
        return authenticationService.getCurrentUser(request);
    }
}