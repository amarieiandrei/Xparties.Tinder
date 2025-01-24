package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.PhotoDto.PhotoResponseDto;
import com.demo.Xparties.Tinder.Model.Entity.Photo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoConverter {

    private final ModelMapper modelMapper;

    public PhotoResponseDto fromEntityToResponseDto(Photo photo) {
        PhotoResponseDto photoResponseDto = modelMapper.map(photo, PhotoResponseDto.class);

        // TODO: relationships and everything

        return photoResponseDto;
    }

}
