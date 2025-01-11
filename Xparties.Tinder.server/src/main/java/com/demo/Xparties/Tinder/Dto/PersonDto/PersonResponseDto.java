package com.demo.Xparties.Tinder.Dto.PersonDto;

import jakarta.validation.Valid;
import lombok.*;

@Valid
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonResponseDto {

    private String externalId;

    private String email;

    private String firstName;

    private String lastName;

    private Integer age;

    private Character gender;

    // TODO: create password important for Person

    // TODO: One to many relationship
    //    private List<Photo> photos;
}
