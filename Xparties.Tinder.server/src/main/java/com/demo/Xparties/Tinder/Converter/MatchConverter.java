package com.demo.Xparties.Tinder.Converter;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Model.Entity.Match;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchConverter {

    private final ModelMapper modelMapper;

    public Match fromRequestDtoToEntity(MatchRequestDto matchRequestDto) {
        return modelMapper.map(matchRequestDto, Match.class);
    }

    public MatchResponseDto fromEntityToResponseDto(Match match) {
        return modelMapper.map(match, MatchResponseDto.class);
    }
}
