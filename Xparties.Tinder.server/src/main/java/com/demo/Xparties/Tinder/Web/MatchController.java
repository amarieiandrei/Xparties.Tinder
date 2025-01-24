package com.demo.Xparties.Tinder.Web;

import com.demo.Xparties.Tinder.Dto.MatchDto.MatchRequestDto;
import com.demo.Xparties.Tinder.Dto.MatchDto.MatchResponseDto;
import com.demo.Xparties.Tinder.Dto.PersonDto.PersonResponseDto;
import com.demo.Xparties.Tinder.Service.Match.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/match")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MatchController {

    private final MatchService matchService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MatchResponseDto createMatch(@Valid @RequestBody MatchRequestDto matchRequestDto) {
        return matchService.createMatch(matchRequestDto);
    }

    @GetMapping(path = "/{eventExternalId}/persons")
    public Page<PersonResponseDto> getAllMatchedPersonsByEvent(@PathVariable String eventExternalId, Pageable pageable) {
        return matchService.getAllMatchedPersonsByEvent(eventExternalId, pageable);
    }
}