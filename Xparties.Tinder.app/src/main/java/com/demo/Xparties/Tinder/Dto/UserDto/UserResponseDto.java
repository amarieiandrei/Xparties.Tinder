package com.demo.Xparties.Tinder.Dto.UserDto;

import com.demo.Xparties.Tinder.Model.Enums.OAuth2Provider;
import jakarta.validation.Valid;
import lombok.*;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDto {

    private String name;

    private String email;

    private OAuth2Provider provider;
}