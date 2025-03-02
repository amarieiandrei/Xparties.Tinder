package com.demo.Xparties.Tinder.Service.Authentication;

import com.demo.Xparties.Tinder.Dto.UserDto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface IAuthenticationService {

    Boolean checkAuthentication(Authentication authentication);
    UserResponseDto getCurrentUser(HttpServletRequest request);
}
