package com.demo.Xparties.Tinder.Dto.MatchDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MatchResponseDto {

    // TODO: refactoring ( I am sending ids from databases only for testing purpose, I should change back with external ids :) )
    private Long personId;

    private Long targetPersonId;

    private Integer eventId;

    private Boolean smash;
}
