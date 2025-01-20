package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Service.Match.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/match")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchController {

    private final MatchService matchService;

    @PostMapping()
    public MatchResponseDto createMatch(@RequestBody MatchRequestDto matchRequestDto) {
        return matchService.createMatch(matchRequestDto);
    }
}