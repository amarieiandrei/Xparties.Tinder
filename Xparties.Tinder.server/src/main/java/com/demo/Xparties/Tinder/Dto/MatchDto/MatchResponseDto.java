package com.demo.Xparties.Tinder.Dto.MatchDto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MatchResponseDto {

    private Long personId;

    private Long targetPersonId;

    private Integer eventId;

    private Boolean smash;
}
