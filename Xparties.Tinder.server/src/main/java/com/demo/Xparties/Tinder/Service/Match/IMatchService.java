package com.demo.Xparties.Tinder.Service.Match;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;

public interface IMatchService {

    MatchResponseDto createMatch(MatchRequestDto matchRequestDto);
}