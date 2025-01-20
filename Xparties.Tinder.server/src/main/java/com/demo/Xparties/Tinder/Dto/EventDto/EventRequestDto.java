package com.demo.Xparties.Tinder.Dto.EventDto;

import com.demo.Xparties.Tinder.Model.Enums.EventCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = false)
public class EventRequestDto {

    @NotNull
    private String name;

    @NotNull
    private Date date;

    @NotNull
    private EventCategory category;
}
