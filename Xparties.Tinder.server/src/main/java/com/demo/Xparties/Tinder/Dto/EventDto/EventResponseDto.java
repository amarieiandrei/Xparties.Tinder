package com.demo.Xparties.Tinder.Dto.EventDto;

import com.demo.Xparties.Tinder.Model.Enums.EventCategory;
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

    private EventCategory category;
}
