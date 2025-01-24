package com.demo.Xparties.Tinder.Dto.MatchDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = false)
public class MatchRequestDto {

    @NotNull
    private String personExternalId;

    @NotNull
    private String targetPersonExternalId;

    @NotNull
    private String eventExternalId;

    @NotNull
    private Boolean smash;
}