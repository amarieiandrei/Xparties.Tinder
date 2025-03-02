package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.UserDto.UserResponseDto;
import com.demo.Xparties.Tinder.Model.Entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserResponseDto fromEntityToResponseDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}
