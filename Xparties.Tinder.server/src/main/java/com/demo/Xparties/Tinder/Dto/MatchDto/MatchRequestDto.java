package com.demo.Xparties.Tinder.Dto.MatchDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MatchRequestDto {

    @NotNull
    private Long personId;

    @NotNull
    private Long targetPersonId;

    @NotNull
    private Integer eventId;

    @NotNull
    private Boolean smash;
}
