package com.demo.Xparties.Tinder.Service.Match;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMatchService {

    MatchResponseDto createMatch(MatchRequestDto matchRequestDto);
    Page<PersonResponseDto> getAllMatchedPersonsByEvent(String eventExternalId, Pageable pageable);
}