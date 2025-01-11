package com.demo.Xparties.Tinder.Dto.PhotoDto;

import jakarta.validation.Valid;
import lombok.*;

@Valid
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PhotoResponseDto {

    private String externalId;

    private String fileName;

    private String contentType;
}