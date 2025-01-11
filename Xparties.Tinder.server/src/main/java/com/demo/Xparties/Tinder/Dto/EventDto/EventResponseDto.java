package com.demo.Xparties.Tinder.Dto.EventDto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EventResponseDto {

    private String externalId;

    private String name;

    private Date date;

//    TODO: understand
//    private Set<PersonResponseDto> personSet;
}
